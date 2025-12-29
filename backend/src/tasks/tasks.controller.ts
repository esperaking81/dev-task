import {
  Controller,
  Get,
  Post,
  Body,
  Patch,
  Param,
  Delete,
  UseGuards,
  Request,
  Query,
} from '@nestjs/common';

import { ApiQuery } from '@nestjs/swagger';

import {
  ApiTags,
  ApiOperation,
  ApiResponse,
  ApiBearerAuth,
} from '@nestjs/swagger';

import { TasksService } from './tasks.service';

import {
  CreateTaskDto,
  UpdateTaskDto,
  TaskStatus,
  AcceptBreakdownDto,
} from './dto/task.dto';

import { JwtAuthGuard } from '../auth/jwt-auth.guard';

import type { AuthenticatedRequest } from '../common/types/auth.types';

@ApiTags('tasks')
@Controller('tasks')
@UseGuards(JwtAuthGuard)
@ApiBearerAuth()
export class TasksController {
  constructor(private readonly tasksService: TasksService) {}

  @Post()
  @ApiOperation({ summary: 'Create a new task' })
  @ApiResponse({ status: 201, description: 'Task created successfully' })
  create(
    @Request() req: AuthenticatedRequest,
    @Body() createTaskDto: CreateTaskDto,
  ) {
    return this.tasksService.create(req.user.id, createTaskDto);
  }

  @Get()
  @ApiOperation({ summary: 'Get all tasks for the authenticated user' })
  @ApiResponse({ status: 200, description: 'Tasks retrieved successfully' })
  @ApiQuery({
    name: 'status',
    required: false,
    enum: TaskStatus,
    description: 'Filter tasks by status',
  })
  findAll(
    @Request() req: AuthenticatedRequest,
    @Query('status') status?: string,
  ) {
    return this.tasksService.findAll(req.user.id, status as TaskStatus);
  }

  @Get(':id')
  @ApiOperation({ summary: 'Get a specific task by ID' })
  @ApiResponse({ status: 200, description: 'Task retrieved successfully' })
  @ApiResponse({ status: 404, description: 'Task not found' })
  findOne(@Request() req: AuthenticatedRequest, @Param('id') id: string) {
    return this.tasksService.findOne(req.user.id, id);
  }

  @Patch(':id')
  @ApiOperation({ summary: 'Update a task' })
  @ApiResponse({ status: 200, description: 'Task updated successfully' })
  @ApiResponse({ status: 404, description: 'Task not found' })
  update(
    @Request() req: AuthenticatedRequest,
    @Param('id') id: string,
    @Body() updateTaskDto: UpdateTaskDto,
  ) {
    return this.tasksService.update(req.user.id, id, updateTaskDto);
  }

  @Post(':id/break-down')
  @ApiOperation({
    summary: 'Generate breakdown suggestions for a task using AI',
  })
  @ApiResponse({
    status: 200,
    description: 'Breakdown suggestions generated or retrieved',
  })
  @ApiResponse({ status: 404, description: 'Task not found' })
  @ApiResponse({
    status: 409,
    description:
      'Cannot generate new breakdown while pending suggestions exist',
  })
  breakDown(@Request() req: AuthenticatedRequest, @Param('id') id: string) {
    return this.tasksService.breakdownTask(req.user.id, id);
  }

  @Post(':id/accept-breakdown')
  @ApiOperation({
    summary: 'Accept and create subtasks from breakdown suggestions',
  })
  @ApiResponse({ status: 200, description: 'Subtasks created successfully' })
  @ApiResponse({ status: 404, description: 'Task not found' })
  acceptBreakdown(
    @Request() req: AuthenticatedRequest,
    @Param('id') id: string,
    @Body() acceptBreakdownDto: AcceptBreakdownDto,
  ) {
    return this.tasksService.acceptBreakdown(
      req.user.id,
      id,
      acceptBreakdownDto,
    );
  }

  @Delete(':id/pending-breakdown')
  @ApiOperation({ summary: 'Clear pending breakdown suggestions' })
  @ApiResponse({ status: 200, description: 'Pending breakdown cleared' })
  @ApiResponse({ status: 404, description: 'Task not found' })
  clearPendingBreakdown(
    @Request() req: AuthenticatedRequest,
    @Param('id') id: string,
  ) {
    return this.tasksService.clearPendingBreakdown(req.user.id, id);
  }

  @Delete(':id')
  @ApiOperation({ summary: 'Delete a task' })
  @ApiResponse({ status: 200, description: 'Task deleted successfully' })
  @ApiResponse({ status: 404, description: 'Task not found' })
  remove(@Request() req: AuthenticatedRequest, @Param('id') id: string) {
    return this.tasksService.remove(req.user.id, id);
  }
}
