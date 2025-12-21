/* eslint-disable @typescript-eslint/no-unsafe-assignment, @typescript-eslint/no-unsafe-member-access, @typescript-eslint/no-unsafe-return */
import {
  Injectable,
  NestInterceptor,
  ExecutionContext,
  CallHandler,
  Logger,
} from '@nestjs/common';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable()
export class LoggingInterceptor implements NestInterceptor {
  private readonly logger = new Logger('RequestLogger');

  intercept(context: ExecutionContext, next: CallHandler): Observable<any> {
    const request = context.switchToHttp().getRequest();
    const response = context.switchToHttp().getResponse();
    const { method, url, body } = request;
    const maskedBody = this.maskSensitiveData(body);
    const start = process.hrtime.bigint();

    this.logger.log(`${method} ${url} Body: ${maskedBody}`);

    return next.handle().pipe(
      tap(() => {
        const end = process.hrtime.bigint();
        const time = Number(end - start) / 1000000; // ms
        this.logger.log(
          `${method} ${url} -> ${response.statusCode} ${time.toFixed(2)}ms`,
        );
      }),
    );
  }

  private maskSensitiveData(data: any): string {
    const sensitiveKeys = ['password', 'token', 'authorization', 'apikey'];
    if (typeof data === 'object' && data !== null) {
      const masked = JSON.stringify(data, (key, value) => {
        if (sensitiveKeys.includes(key.toLowerCase())) {
          return '[MASKED]';
        }
        return value;
      });
      return masked.length > 1000 ? masked.substring(0, 1000) + '...' : masked;
    }
    return JSON.stringify(data) || 'N/A';
  }
}
