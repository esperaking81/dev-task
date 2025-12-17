import { Injectable } from '@nestjs/common';
import { PrismaService } from '../prisma/prisma.service';
import { CreateTaskDto, UpdateTaskDto, TaskStatus } from './dto/task.dto';
import { AiService } from 'src/ai/ai.service';

@Injectable()
export class TasksService {
  constructor(
    private prisma: PrismaService,
    private aiService: AiService,
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

    const taskTitle = task.title;

    const subtasks = await this.aiService.breakdownTask(taskTitle);
    if (subtasks.length > 0) {
      await this.prisma.$transaction(async (tx) => {
        await tx.task.createMany({
          data: subtasks.map((subtask) => ({
            title: subtask.title || '',
            description: subtask.description || undefined,
            order: subtask.order || 0,
            status: TaskStatus.TODO,
            userId,
            parentId: id,
          })),
        });
      });
    }

    return this.findOne(userId, id);
  }
}
