# DevTask

AI-powered task management for developers. Break down complex development tasks, track progress, and boost productivity with intelligent task decomposition.

## Overview

DevTask helps developers manage their work more effectively by leveraging AI to automatically break down high-level tasks into actionable subtasks. Perfect for solo developers and small teams looking to improve workflow efficiency.

## Tech Stack

### Backend

- **NestJS** - Scalable Node.js framework
- **PostgreSQL** - Primary database
- **TypeORM** - Database ORM
- **JWT** - Authentication

### Frontend (Web)

- **Next.js** - React framework
- **TypeScript** - Type safety
- **Tailwind CSS** - Styling

### Mobile (Coming Soon)

- **Flutter** - Cross-platform mobile framework
- **Dart** - Programming language

## Features

- 🤖 **AI Task Breakdown** - Automatically decompose complex tasks into manageable subtasks
- ✅ **Task Management** - Create, update, and track development tasks
- 📊 **Progress Tracking** - Monitor task completion and project status
- 🔐 **Authentication** - Secure user authentication and authorization
- 📱 **Multi-platform** - Web app available now, mobile app coming soon

## Project Structure

```
devtask/
├── backend/          # NestJS API server
├── frontend/         # Next.js web application
└── mobile/          # Flutter mobile app (upcoming)
```

## Getting Started

### Prerequisites

- Node.js 18+
- PostgreSQL 14+
- pnpm/npm/yarn
- Docker (optional)

### Backend Setup

```bash
cd backend
npm install
cp .env.example .env
# Configure your environment variables
npm run start:dev
```

### Frontend Setup

```bash
cd frontend
npm install
cp .env.example .env.local
# Configure your environment variables
npm run dev
```

### Docker Setup (Optional)

```bash
docker-compose up -d
```

## Environment Variables

### Backend

```env
DATABASE_URL=postgresql://user:password@localhost:5432/devtask
JWT_SECRET=your-secret-key
OPENAI_API_KEY=your-openai-key
```

### Frontend

```env
NEXT_PUBLIC_API_URL=http://localhost:3001
```

## API Documentation

API documentation is available at `http://localhost:3001/api/docs` when running the backend in development mode.

## Development

### Backend

```bash
npm run start:dev     # Start development server
npm run test          # Run tests
npm run build         # Build for production
```

### Frontend

```bash
npm run dev           # Start development server
npm run build         # Build for production
npm run lint          # Lint code
```

## Deployment

### Backend

- Build: `npm run build`
- Start: `npm run start:prod`

### Frontend

- Build: `npm run build`
- Deploy to Vercel, Netlify, or your preferred platform

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Roadmap

- [x] Core task management
- [x] AI task breakdown
- [ ] Web application
- [ ] Mobile application (Flutter)
- [ ] Team collaboration features
- [ ] Integration with GitHub/GitLab
- [ ] Advanced AI features (task estimation, priority suggestions)

## Contact

For questions or support, please open an issue on GitHub.

---

Built with ❤️ for developers who want to work smarter, not harder.
