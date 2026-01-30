import { API_ENDPOINTS } from "../api/endpoints";
import { serverFetcher } from "./api-client";
import { Task } from "../types/task";

export const tasksApi = {
  async getAll(): Promise<Task[]> {
    return await serverFetcher<Task[]>(API_ENDPOINTS.tasks.list);
  },
  async getById(id: string) {
    return await serverFetcher<Task>(API_ENDPOINTS.tasks.byId(id));
  },
};
