import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { PrismaModule } from './prisma/prisma.module';
import { AuthModule } from './auth/auth.module';
import { UserModule } from './user/user.module';
import { TasksModule } from './tasks/tasks.module';
import { AiModule } from './ai/ai.module';
import { TagsModule } from './tags/tags.module';
import { RedisModule } from './redis/redis.module';

@Module({
  imports: [
    PrismaModule,
    AuthModule,
    UserModule,
    TasksModule,
    AiModule,
    TagsModule,
    RedisModule,
  ],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
