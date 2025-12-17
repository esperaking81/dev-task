export interface Task {
    id: string;
    title: string;
    description: string;
    status: 'TODO' | 'IN_PROGRESS' | 'DONE';
    tags: Tag[];
    subtasks: Subtask[];
    createdAt: string;
}

export interface Tag {
    id: string;
    name: string;
    color: string;
    createdAt: string;
}

export interface Subtask {
    id: string;
    title: string;
    completed: boolean;
}
