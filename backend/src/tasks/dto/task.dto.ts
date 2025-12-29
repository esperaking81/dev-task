import {
  IsString,
  IsOptional,
  IsEnum,
  IsInt,
  IsUUID,
  IsDateString,
} from 'class-validator';
import { ApiProperty, ApiPropertyOptional } from '@nestjs/swagger';

export enum TaskStatus {
  TODO = 'TODO',
  IN_PROGRESS = 'IN_PROGRESS',
  DONE = 'DONE',
}

export enum TaskPriority {
  LOW = 'LOW',
  MEDIUM = 'MEDIUM',
  HIGH = 'HIGH',
}

export type TaskStatusOptional = TaskStatus;

export class BreakdownSuggestionDto {
  @ApiProperty({ description: 'Task title' })
  @IsString()
  title: string;

  @ApiPropertyOptional({ description: 'Task description' })
  @IsOptional()
  @IsString()
  description?: string;

  @ApiPropertyOptional({
    enum: TaskPriority,
    default: TaskPriority.MEDIUM,
    enumName: 'TaskPriority',
  })
  @IsOptional()
  @IsEnum(TaskPriority)
  priority?: TaskPriority = TaskPriority.MEDIUM;

  @ApiPropertyOptional({ description: 'Task order', default: 0 })
  @IsOptional()
  @IsInt()
  order?: number = 0;
}

export class AcceptBreakdownDto {
  @ApiProperty({ type: [BreakdownSuggestionDto] })
  subtasks: BreakdownSuggestionDto[];
}

export class CreateTaskDto {
  @ApiProperty({ description: 'Task title' })
  @IsString()
  title: string;

  @ApiPropertyOptional({ description: 'Task description' })
  @IsOptional()
  @IsString()
  description?: string;

  @ApiPropertyOptional({
    enum: TaskStatus,
    default: TaskStatus.TODO,
    enumName: 'TaskStatus',
  })
  @IsOptional()
  @IsEnum(TaskStatus)
  status?: TaskStatus = TaskStatus.TODO;

  @ApiPropertyOptional({ description: 'Task order for sorting', default: 0 })
  @IsOptional()
  @IsInt()
  order?: number = 0;

  @ApiPropertyOptional({ description: 'Parent task ID for subtasks' })
  @IsOptional()
  @IsUUID()
  parentId?: string;

  @ApiPropertyOptional({ description: 'Array of tag IDs', type: [String] })
  @IsOptional()
  @IsUUID()
  tagIds?: string[];

  @ApiPropertyOptional({ description: 'Task due date', type: String })
  @IsOptional()
  @IsDateString()
  dueDate?: string;

  @ApiPropertyOptional({
    enum: TaskPriority,
    default: TaskPriority.MEDIUM,
    enumName: 'TaskPriority',
  })
  @IsOptional()
  @IsEnum(TaskPriority)
  priority?: TaskPriority = TaskPriority.MEDIUM;

  @ApiPropertyOptional({
    description: 'Array of assignee user IDs',
    type: [String],
  })
  @IsOptional()
  @IsUUID(undefined, { each: true })
  assigneeIds?: string[];
}

export class UpdateTaskDto {
  @ApiPropertyOptional({ description: 'Task title' })
  @IsOptional()
  @IsString()
  title?: string;

  @ApiPropertyOptional({ description: 'Task description' })
  @IsOptional()
  @IsString()
  description?: string;

  @ApiPropertyOptional({
    enum: TaskStatus,
    enumName: 'TaskStatus',
  })
  @IsOptional()
  @IsEnum(TaskStatus)
  status?: TaskStatus;

  @ApiPropertyOptional({ description: 'Task order for sorting' })
  @IsOptional()
  @IsInt()
  order?: number;

  @ApiPropertyOptional({ description: 'Parent task ID for subtasks' })
  @IsOptional()
  @IsUUID()
  parentId?: string;

  @ApiPropertyOptional({ description: 'Array of tag IDs', type: [String] })
  @IsOptional()
  @IsUUID()
  tagIds?: string[];

  @ApiPropertyOptional({ description: 'Task due date', type: String })
  @IsOptional()
  @IsDateString()
  dueDate?: string;

  @ApiPropertyOptional({
    enum: TaskPriority,
    enumName: 'TaskPriority',
  })
  @IsOptional()
  @IsEnum(TaskPriority)
  priority?: TaskPriority;

  @ApiPropertyOptional({
    description: 'Array of assignee user IDs',
    type: [String],
  })
  @IsOptional()
  @IsUUID(undefined, { each: true })
  assigneeIds?: string[];
}
