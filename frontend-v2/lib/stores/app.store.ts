import { create } from "zustand";
import { User } from "../types/user";
import { devtools, persist } from "zustand/middleware";

export type AppState = {
  user?: User;
};

export const useAppStore = create<AppState>()(
  devtools(
    persist(
      (set) => ({
        user: undefined,
      }),
      { name: "devtask-store" },
    ),
  ),
);

export const setUser = (user?: User) => useAppStore.setState({ user });
