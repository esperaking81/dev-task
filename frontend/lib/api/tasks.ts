import { fetcher } from "./client";
import { API_ENDPOINTS } from "./endpoints";
import { Task } from "../types/task";

export const tasksApi = {
  async getAll() {
    const response = await fetcher(API_ENDPOINTS.tasks.list);
    if (!response.ok) {
      throw new Error("Failed to fetch tasks");
    }
    return response.json() as Promise<Task[]>;
  },

  async getById(id: string) {
    const response = await fetcher(API_ENDPOINTS.tasks.byId(id));
    if (!response.ok) {
      throw new Error("Failed to fetch task");
    }
    return response.json() as Promise<Task>;
  },

  async create(data: Partial<Task>) {
    const response = await fetcher(API_ENDPOINTS.tasks.create, {
      method: "POST",
      body: JSON.stringify(data),
    });
    if (!response.ok) {
      throw new Error("Failed to create task");
    }
    return response.json() as Promise<Task>;
  },

  async update(id: string, data: Partial<Task>) {
    const response = await fetcher(API_ENDPOINTS.tasks.update(id), {
      method: "PUT",
      body: JSON.stringify(data),
    });
    if (!response.ok) {
      throw new Error("Failed to update task");
    }
    return response.json() as Promise<Task>;
  },

  async delete(id: string) {
    const response = await fetcher(API_ENDPOINTS.tasks.delete(id), {
      method: "DELETE",
    });
    if (!response.ok) {
      throw new Error("Failed to delete task");
    }
    return response.json() as Promise<Task>;
  },

  async breakDown(id: string) {
    const response = await fetcher(API_ENDPOINTS.tasks.breakdown(id), {
      method: "POST",
    });
    if (!response.ok) {
      throw new Error("Failed to break down task");
    }
    return response.json() as Promise<Task>;
  },

  async acceptBreakdown(id: string) {
    const response = await fetcher(API_ENDPOINTS.tasks.acceptBreakdown(id), {
      method: "POST",
    });
    if (!response.ok) {
      throw new Error("Failed to accept breakdown");
    }
    return response.json() as Promise<Task>;
  },

  async clearPendingBreakdown(id: string) {
    const response = await fetcher(API_ENDPOINTS.tasks.clearPendingBreakdown(id), {
      method: "POST",
    });
    if (!response.ok) {
      throw new Error("Failed to clear pending breakdown");
    }
    return response.json() as Promise<Task>;
  },
};