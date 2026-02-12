# DevTask

AI-powered task management for developers. Break down complex development tasks, track progress, and boost productivity with intelligent task decomposition.

## Overview

DevTask helps developers manage their work more effectively by leveraging AI to automatically break down high-level tasks into actionable subtasks. Perfect for solo developers and small teams looking to improve workflow efficiency.

## Tech Stack

### Backend

- **NestJS** - Scalable Node.js framework (v11)
- **Bun** - JavaScript runtime & package manager
- **PostgreSQL** - Primary database
- **Prisma** - Type-safe Database ORM
- **JWT/Passport** - Authentication

### Frontend (Web)

- **Next.js 16** - React framework (App Router)
- **Bun** - JavaScript runtime & package manager
- **TypeScript** - Type safety
- **Tailwind CSS v4** - Styling
- **Radix UI** - Accessible UI components
- **Zustand** - State management

### Mobile (Android)

- **Kotlin** - Native Android development
- **Jetpack Compose** - Modern UI toolkit
- **Koin** - Dependency Injection
- **Ktor** - Networking client
- **Room** - Local database

## Project Structure

```
devtask/
├── backend/          # NestJS API server (Bun + Prisma)
├── frontend/         # Next.js web application (Bun + Tailwind v4)
└── DevTaskKMM/          # Native Android app (Kotlin + Compose Multiplatform)
```

## Getting Started

### Prerequisites

- [Bun](https://bun.sh) (latest)
- PostgreSQL 14+
- Android Studio (for mobile app)
- Docker (optional)

### Backend Setup

```bash
cd backend
bun install
# Create .env file (see Environment Variables section)
# Run database migrations
bun prisma migrate dev
# Start development server
bun run start:dev
```

### Frontend Setup

```bash
cd frontend
bun install
# Create .env.local file (see Environment Variables section)
# Configure your environment variables
bun run dev
```

### Mobile App Setup

1. Open `DevTask/` in Android Studio.
2. Sync Gradle project.
3. Run on an emulator or physical device.

### Docker Setup (Optional)

```bash
docker-compose -f compose.dev.yml up -d
```

## Environment Variables

### Backend (.env)

```env
# Note: Port 5433 is used if running via docker-compose.dev.yml
DATABASE_URL="postgresql://username:password@host:port/devtask_db?schema=public"
JWT_SECRET="your-secret-key"
PORT=4000
```

### Frontend (.env.local)

```env
NEXT_PUBLIC_API_URL="http://localhost:4000"
```

## API Documentation

API documentation is available at `http://localhost:4000/api` when running the backend in development mode (Swagger UI).

## Development Commands

### Backend

```bash
bun run start:dev     # Start development server
bun run test          # Run tests
bun run build         # Build for production
bun run lint          # Lint code
```

### Frontend

```bash
bun run dev           # Start development server
bun run build         # Build for production
bun run lint          # Lint code
```

## Deployment

### Backend

- Build: `bun run build`
- Start: `bun run start:prod` (or `node dist/main.js`)

### Frontend

- Build: `bun run build`
- Deploy to Vercel, Netlify, or self-hosted Node.js/Bun server.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.
