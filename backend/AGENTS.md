# Development Guidelines for Agentic Coding Agents

## Commands
- Build: `bun run build`
- Dev server: `bun run start:dev`
- Run tests: `bun run test` or `jest`
- Run single test: `bun run test path/to/file.spec.ts` or `jest path/to/file.spec.ts`
- Lint: `bun eslint .` or `eslint .`
- Type check: `bun run build` (build process includes type checking)

## Code Style
- Prettier: Single quotes, trailing commas all
- Import order: External framework → Node.js built-ins → Internal imports → Type imports
- File naming: kebab-case directories, PascalCase classes (UserController, user.service.ts)
- NestJS patterns: Controllers → Services → Modules with consistent dependency injection
- DTOs: Use class-validator decorators, separate Create/Update DTOs
- Error handling: Simple Error throws in services, no custom error classes
- Database: Use Prisma with consistent select patterns, userSelectWithoutPassword for user queries
- Testing: Standard Jest patterns with beforeEach setup, mock dependencies
- Authentication: Use AuthenticatedRequest interface, JWTAuthGuard for protected routes
- Environment: Access via process.env at runtime, no config classes