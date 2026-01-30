import { TaskPriority } from "./task";

export interface BreakdownSuggestion {
  title: string;
  description?: string;
  priority: TaskPriority;
  order: number;
}
