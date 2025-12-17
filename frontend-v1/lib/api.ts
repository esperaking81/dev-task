const API_URL = "http://localhost:3000"

// Helper to get auth token (mocked for now, or stored in localStorage if we implement full auth)
// For the test credentials provided (dev@devtask.com/password123), we would normally POST /auth/login
// But to keep moving fast, I'll implement the login method and store the token.

let authToken = "";

export const api = {
    setToken: (token: string) => {
        authToken = token;
        if (typeof window !== 'undefined') {
            localStorage.setItem("devtask_token", token);
        }
    },

    getToken: () => {
        if (!authToken && typeof window !== 'undefined') {
            authToken = localStorage.getItem("devtask_token") || "";
        }
        return authToken;
    },

    auth: {
        login: async (email: string, password: string) => {
            const res = await fetch(`${API_URL}/auth/login`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ email, password }),
            });
            if (!res.ok) throw new Error("Login failed");
            const data = await res.json();
            return data; // Expecting { acces_token: ... }
        }
    },

    tasks: {
        getAll: async () => {
            const res = await fetch(`${API_URL}/tasks`, {
                headers: { Authorization: `Bearer ${api.getToken()}` }
            });
            return res.json();
        },

        create: async (task: any) => {
            const res = await fetch(`${API_URL}/tasks`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${api.getToken()}`
                },
                body: JSON.stringify(task)
            });
            return res.json();
        },

        update: async (id: string, updates: any) => {
            const res = await fetch(`${API_URL}/tasks/${id}`, {
                method: "PATCH",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${api.getToken()}`
                },
                body: JSON.stringify(updates)
            });
            return res.json();
        },

        delete: async (id: string) => {
            await fetch(`${API_URL}/tasks/${id}`, {
                method: "DELETE",
                headers: { Authorization: `Bearer ${api.getToken()}` }
            });
        },

        breakDown: async (id: string) => {
            const res = await fetch(`${API_URL}/tasks/${id}/break-down`, {
                method: "POST",
                headers: { Authorization: `Bearer ${api.getToken()}` }
            });
            return res.json();
            return res.json();
        }
    },

    tags: {
        getAll: async () => {
            const res = await fetch(`${API_URL}/tags`, {
                headers: { Authorization: `Bearer ${api.getToken()}` }
            });
            return res.json();
        }
    }
}
