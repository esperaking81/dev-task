export const API_ENDPOINTS = {
  auth: {
    login: "/auth/login",
    signUp: "/auth/sign-up",
    getProfile: "/auth/profile",
  },
  tasks: {
    create: "/tasks",
    list: "/tasks",
    byId: (id: string) => `/tasks/${id}`,
    delete: (id: string) => `/tasks/${id}`,
    update: (id: string) => `/tasks/${id}`,
    breakdown: (id: string) => `/tasks/${id}/break-down`,
    acceptBreakdown: (id: string) => `/tasks/${id}/accept-breakdown`,
    clearPendingBreakdown: (id: string) => `/tasks/${id}/pending-breakdown`,
  },
  tags: {
    create: "/tags",
    list: "/tags",
    byId: (id: string) => `/tags/${id}`,
    update: (id: string) => `/tags/${id}`,
    delete: (id: string) => `/tags/${id}`,
  },
};
