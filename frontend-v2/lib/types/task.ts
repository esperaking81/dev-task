export interface Task {
  id: string
  title: string
  description: string | null
  status: string
  order: number
  dueDate: string | null
  priority: string
  createdAt: string
  updatedAt: string
  userId: string
  assigneeId: string | null
  parentId: string | null
  subtasks?: Task[]
  tags?: Tag[]
  user?: User
  assignee?: User | null
  parent?: Task | null
}

export interface Tag {
  id: string
  name: string
  color: string | null
  createdAt: string
  updatedAt: string
}

export interface User {
  id: string
  email: string
  firstName: string | null
  lastName: string | null
  createdAt: string
  updatedAt: string
}