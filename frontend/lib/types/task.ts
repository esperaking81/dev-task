export interface Task {
  id: string;
  title: string;
  description: string | null;
  status: string;
  order: number;
  dueDate: string | null;
  priority: string;
  createdAt: string;
  updatedAt: string;
  userId: string;
  parentId: string | null;
  subtasks?: Task[];
  tags: Tag[] | [];
  user?: User;
  assignees: User[];
  parent?: Task | null;
}

export interface Tag {
  id: string;
  name: string;
  color: string | null;
  createdAt: string;
  updatedAt: string;
}

export interface User {
  id: string;
  email: string;
  firstName: string | null;
  lastName: string | null;
  createdAt: string;
  updatedAt: string;
}

export enum TaskStatus {
  TODO = "TODO",
  IN_PROGRESS = "IN_PROGRESS",
  DONE = "DONE",
}

export enum TaskPriority {
  LOW = "LOW",
  MEDIUM = "MEDIUM",
  HIGH = "HIGH",
}
