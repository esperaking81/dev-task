"use client";

import { Task } from "@/lib/types/task";
import { DataTable } from "../ui/data-table";
import { columns } from "./columns";

const defaultData: Task[] = [
  {
    id: "refactor-authentication-api",
    title: "Refactor Authentication API",
    description:
      "Refactor the authentication API to use a more modern approach",
    status: "IN_PROGRESS",
    assignees: [
      {
        id: "user-1",
        lastName: "Doe",
        firstName: "John",
        email: "john.doe@example.com",
        createdAt: "2023-05-01T00:00:00.000Z",
        updatedAt: "2023-05-01T00:00:00.000Z",
      },
    ],
    order: 0,
    createdAt: "2023-05-01T00:00:00.000Z",
    updatedAt: "2023-05-01T00:00:00.000Z",
    dueDate: "2023-05-01T00:00:00.000Z",
    priority: "HIGH",
    parentId: null,
    userId: "user-1",
    tags: [
      {
        id: "tag-backend",
        name: "Backend",
        color: "#F87171",
        createdAt: "",
        updatedAt: "",
      },
    ],
  },
  {
    id: "system-update",
    title: "Design System Update v2.0",
    description:
      "Design a new and improved version of the system, focusing on user experience and usability",
    status: "TODO",
    assignees: [
      {
        id: "user-1",
        lastName: "Doe",
        firstName: "John",
        email: "john.doe@example.com",
        createdAt: "2023-05-01T00:00:00.000Z",
        updatedAt: "2023-05-01T00:00:00.000Z",
      },
    ],
    order: 0,
    createdAt: "2023-05-01T00:00:00.000Z",
    updatedAt: "2023-05-01T00:00:00.000Z",
    dueDate: "2023-05-01T00:00:00.000Z",
    priority: "MEDIUM",
    parentId: null,
    userId: "user-1",
    tags: [
      {
        id: "tag-backend",
        name: "Backend",
        color: "#F87171",
        createdAt: "",
        updatedAt: "",
      },
    ],
  },
  {
    id: "fix-memory-leak",
    title: "Fix memory leak in worker",
    description:
      "Find and fix the memory leak in the worker process, ensuring efficient memory usage and preventing potential crashes",
    status: "DONE",
    assignees: [
      {
        id: "user-1",
        lastName: "Doe",
        firstName: "John",
        email: "john.doe@example.com",
        createdAt: "2023-05-01T00:00:00.000Z",
        updatedAt: "2023-05-01T00:00:00.000Z",
      },
    ],
    order: 0,
    createdAt: "2023-05-01T00:00:00.000Z",
    updatedAt: "2023-05-01T00:00:00.000Z",
    dueDate: "2023-05-01T00:00:00.000Z",
    priority: "LOW",
    parentId: null,
    userId: "user-1",
    tags: [
      {
        id: "bug",
        name: "Bug",
        color: "#F87171",
        createdAt: "",
        updatedAt: "",
      },
    ],
  },
];

export function TasksList({ data = defaultData }: { data?: Task[] }) {
  return <DataTable columns={columns} data={data} />;
}
