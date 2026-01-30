import { authApi } from "./auth";
import { tasksApi } from "./tasks";

export const api = {
  tasks: tasksApi,
  auth: authApi,
};
