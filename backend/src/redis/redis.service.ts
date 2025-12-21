import {
  Injectable,
  Logger,
  OnModuleDestroy,
  OnModuleInit,
} from '@nestjs/common';
import Redis from 'ioredis';

@Injectable()
export class RedisService implements OnModuleInit, OnModuleDestroy {
  private readonly logger = new Logger(RedisService.name);
  private client: Redis;
  private readonly ttl = parseInt(
    process.env.REDIS_BREAKDOWN_TTL || '86400',
    10,
  );

  async onModuleInit() {
    const host = process.env.REDIS_HOST || 'localhost';
    const port = parseInt(process.env.REDIS_PORT || '6379', 10);

    this.client = new Redis({
      host,
      port,
      maxRetriesPerRequest: 3,
      lazyConnect: true,
    });

    this.client.on('connect', () => {
      this.logger.log('Connected to Redis');
    });

    this.client.on('error', (error) => {
      this.logger.error('Redis connection error', error);
    });

    this.client.on('ready', () => {
      this.logger.log('Redis is ready');
    });

    try {
      await this.client.connect();
    } catch (error) {
      this.logger.error(
        'Failed to connect to Redis, continuing without Redis',
        error,
      );
      // Don't throw, allow app to continue without Redis
    }
  }

  async onModuleDestroy() {
    if (this.client) {
      await this.client.quit();
      this.logger.log('Disconnected from Redis');
    }
  }

  private getBreakdownKey(userId: string, taskId: string): string {
    return `breakdown:${userId}:${taskId}`;
  }

  async setPendingBreakdown(
    userId: string,
    taskId: string,
    suggestions: Record<string, any>[],
  ): Promise<void> {
    try {
      if (!this.client || this.client.status !== 'ready') {
        this.logger.warn('Redis not available, skipping breakdown storage');
        return;
      }

      const key = this.getBreakdownKey(userId, taskId);
      const value = JSON.stringify(suggestions);
      await this.client.setex(key, this.ttl, value);
      this.logger.log(
        `Stored pending breakdown for user ${userId}, task ${taskId}`,
      );
    } catch (error) {
      this.logger.error('Failed to store pending breakdown', error);
    }
  }

  async getPendingBreakdown(
    userId: string,
    taskId: string,
  ): Promise<Record<string, any>[] | null> {
    try {
      if (!this.client || this.client.status !== 'ready') {
        this.logger.warn('Redis not available, returning null');
        return null;
      }

      const key = this.getBreakdownKey(userId, taskId);
      const value = await this.client.get(key);
      if (value) {
        const suggestions = JSON.parse(value) as Record<string, any>[];
        this.logger.log(
          `Retrieved pending breakdown for user ${userId}, task ${taskId}`,
        );
        return suggestions;
      }
      return null;
    } catch (error) {
      this.logger.error('Failed to retrieve pending breakdown', error);
      return null;
    }
  }

  async deletePendingBreakdown(userId: string, taskId: string): Promise<void> {
    try {
      if (!this.client || this.client.status !== 'ready') {
        this.logger.warn('Redis not available, skipping deletion');
        return;
      }

      const key = this.getBreakdownKey(userId, taskId);
      await this.client.del(key);
      this.logger.log(
        `Deleted pending breakdown for user ${userId}, task ${taskId}`,
      );
    } catch (error) {
      this.logger.error('Failed to delete pending breakdown', error);
    }
  }

  getClient(): Redis | null {
    return this.client?.status === 'ready' ? this.client : null;
  }
}
