import { fetcher } from "./client";
import { API_ENDPOINTS } from "./endpoints";
import { Task } from "../types/task";
import { BreakdownSuggestion } from "../types/breakdown";

export const tasksApi = {
  async getAll() {
    return await fetcher<Task[]>(API_ENDPOINTS.tasks.list);
  },

  async getById(id: string) {
    return await fetcher<Task>(API_ENDPOINTS.tasks.byId(id));
  },

  async create(data: Partial<Task>) {
    return await fetcher(API_ENDPOINTS.tasks.create, {
      method: "POST",
      body: JSON.stringify(data),
    });
  },

  async update(id: string, data: Partial<Task>) {
    return await fetcher<Task>(API_ENDPOINTS.tasks.update(id), {
      method: "PUT",
      body: JSON.stringify(data),
    });
  },

  async delete(id: string) {
    return await fetcher<Task>(API_ENDPOINTS.tasks.delete(id), {
      method: "DELETE",
    });
  },

  async breakDown(id: string) {
    const {
      isPending,
      suggestions,
    }: {
      isPending: boolean;
      suggestions: BreakdownSuggestion[];
    } = await fetcher(API_ENDPOINTS.tasks.breakdown(id), {
      method: "POST",
    });

    return { isPending, suggestions };
  },

  async acceptBreakdown(id: string, suggestions: BreakdownSuggestion[]) {
    const data = { subtasks: suggestions };
    return await fetcher<Task>(API_ENDPOINTS.tasks.acceptBreakdown(id), {
      method: "POST",
      body: JSON.stringify(data),
    });
  },

  async clearPendingBreakdown(id: string) {
    await fetcher(API_ENDPOINTS.tasks.clearPendingBreakdown(id), {
      method: "POST",
    });
  },
};

