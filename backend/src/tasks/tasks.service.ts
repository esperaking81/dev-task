import { Injectable } from '@nestjs/common';
import { PrismaService } from '../prisma/prisma.service';
import {
  CreateTaskDto,
  UpdateTaskDto,
  TaskStatus,
  AcceptBreakdownDto,
} from './dto/task.dto';
import { AiService, BreakdownSuggestion } from '../ai/ai.service';
import { RedisService } from '../redis/redis.service';

@Injectable()
export class TasksService {
  constructor(
    private prisma: PrismaService,
    private aiService: AiService,
    private redisService: RedisService,
  ) {}

  async create(userId: string, createTaskDto: CreateTaskDto) {
    const { tagIds, ...taskData } = createTaskDto;

    return await this.prisma.task.create({
      data: {
        ...taskData,
        userId,
        tags: tagIds
          ? {
              connect: tagIds.map((id) => ({ id })),
            }
          : undefined,
      },
      include: {
        tags: true,
        subtasks: true,
        parent: true,
      },
    });
  }

  async findAll(userId: string, status?: TaskStatus) {
    return await this.prisma.task.findMany({
      where: {
        userId,
        ...(status && { status }),
      },
      include: {
        tags: true,
        subtasks: true,
        parent: true,
      },
      orderBy: [{ order: 'asc' }, { createdAt: 'desc' }],
    });
  }

  async findOne(userId: string, id: string) {
    const task = await this.prisma.task.findFirst({
      where: {
        id,
        userId,
      },
      include: {
        tags: true,
        subtasks: true,
        parent: true,
      },
    });

    if (!task) {
      throw new Error('Task not found');
    }

    return task;
  }

  async update(userId: string, id: string, updateTaskDto: UpdateTaskDto) {
    const { tagIds, ...taskData } = updateTaskDto;

    const existingTask = await this.prisma.task.findFirst({
      where: { id, userId },
    });

    if (!existingTask) {
      throw new Error('Task not found');
    }

    return await this.prisma.task.update({
      where: { id },
      data: {
        ...taskData,
        tags: tagIds
          ? {
              set: [],
              connect: tagIds.map((id) => ({ id })),
            }
          : undefined,
      },
      include: {
        tags: true,
        subtasks: true,
        parent: true,
      },
    });
  }

  async remove(userId: string, id: string) {
    const existingTask = await this.prisma.task.findFirst({
      where: { id, userId },
    });

    if (!existingTask) {
      throw new Error('Task not found');
    }

    return await this.prisma.task.delete({
      where: { id },
    });
  }

  async breakdownTask(userId: string, id: string) {
    const task = await this.prisma.task.findFirst({
      where: { id, userId },
    });

    if (!task) {
      throw new Error('Task not found');
    }

    // Check if there are pending suggestions
    const existingSuggestions = await this.redisService.getPendingBreakdown(
      userId,
      id,
    );
    if (existingSuggestions) {
      return {
        suggestions: existingSuggestions as BreakdownSuggestion[],
        isPending: true,
      };
    }

    // Generate new suggestions
    const taskTitle = task.title;
    const suggestions = await this.aiService.breakdownTask(taskTitle);

    // Store in Redis
    await this.redisService.setPendingBreakdown(userId, id, suggestions);

    return { suggestions, isPending: false };
  }

  async acceptBreakdown(
    userId: string,
    id: string,
    acceptDto: AcceptBreakdownDto,
  ) {
    const task = await this.prisma.task.findFirst({
      where: { id, userId },
    });

    if (!task) {
      throw new Error('Task not found');
    }

    const subtasks = acceptDto.subtasks;
    if (subtasks.length > 0) {
      await this.prisma.$transaction(async (tx) => {
        await tx.task.createMany({
          data: subtasks.map((subtask, index) => ({
            title: subtask.title,
            description: subtask.description || undefined,
            order: subtask.order ?? index,
            priority: subtask.priority || 'medium',
            status: TaskStatus.TODO,
            userId,
            parentId: id,
          })),
        });
      });
    }

    // Clear pending breakdown
    await this.redisService.deletePendingBreakdown(userId, id);

    return this.findOne(userId, id);
  }

  async clearPendingBreakdown(userId: string, id: string) {
    // Verify task exists and belongs to user
    const task = await this.prisma.task.findFirst({
      where: { id, userId },
    });

    if (!task) {
      throw new Error('Task not found');
    }

    await this.redisService.deletePendingBreakdown(userId, id);
  }
}
