# AGENTS.md

## Commands
- **Build**: `npm run build` or `next build`
- **Dev server**: `npm run dev` or `next dev`
- **Lint**: `npm run lint` or `eslint`
- **Type check**: `npx tsc --noEmit`
- **Single test**: No test framework configured

## Code Style
- **TypeScript**: Strict mode enabled, explicit types required
- **Imports**: Named imports from libraries, relative imports for local files
- **Naming**: PascalCase for components/interfaces, camelCase for variables/functions, UPPER_CASE for constants
- **Components**: "use client" directive for client components, React.FC type annotation
- **Error handling**: Use try/catch, optional chaining (?.), early returns
- **Formatting**: ESLint with Next.js rules (core web vitals + TypeScript)
- **JSX**: React 19 with react-jsx transform
- **Styling**: Tailwind CSS with custom CSS variables