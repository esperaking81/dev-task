import { NestFactory } from '@nestjs/core';
import { DocumentBuilder, SwaggerModule } from '@nestjs/swagger';
import { AppModule } from './app.module';
import { LoggingInterceptor } from './common/interceptors/logging.interceptor';

const port = process.env.PORT ?? 4000;

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  // Enable CORS
  app.enableCors({
    origin: 'http://localhost:3000',
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
  console.log(`🌐 CORS enabled for: http://localhost:3000`);
}

bootstrap().catch((reason) => {
  console.log('Failed to start server: ', reason);
});
