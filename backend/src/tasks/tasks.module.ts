import { Module } from '@nestjs/common';
import { TasksService } from './tasks.service';
import { TasksController } from './tasks.controller';
import { AiModule } from '../ai/ai.module';
import { RedisModule } from 'src/redis/redis.module';

@Module({
  imports: [AiModule, RedisModule],
  controllers: [TasksController],
  providers: [TasksService],
  exports: [TasksService],
})
export class TasksModule {}
