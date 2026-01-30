import { NestFactory } from '@nestjs/core';
import { DocumentBuilder, SwaggerModule } from '@nestjs/swagger';
import { AppModule } from './app.module';
import { LoggingInterceptor } from './common/interceptors/logging.interceptor';
import cookieParser from 'cookie-parser';

const port = process.env.PORT ?? 4000;
const CORS_ORIGIN = process.env.CORS_ORIGIN ?? 'http://localhost:3000';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  app.use(cookieParser());

  // Enable CORS
  app.enableCors({
    origin: CORS_ORIGIN,
    credentials: true,
  });

  // Add logging interceptor for dev environment
  if (process.env.NODE_ENV === 'development') {
    app.useGlobalInterceptors(new LoggingInterceptor());
  }

  const config = new DocumentBuilder()
    .setTitle('Dev Task API')
    .setDescription('API for task management with AI-powered task breakdown')
    .setVersion('1.0')
    .addBearerAuth()
    .build();

  const document = SwaggerModule.createDocument(app, config);
  SwaggerModule.setup('api', app, document);

  await app.listen(port);

  console.log(`🚀 Server running on: ${await app.getUrl()}`);
  console.log(`📚 Swagger docs available at: ${await app.getUrl()}/api`);
  console.log(`🌐 CORS enabled for: ${CORS_ORIGIN}`);
}

bootstrap().catch((reason) => {
  console.log('Failed to start server: ', reason);
});
