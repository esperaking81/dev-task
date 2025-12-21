"use server";

import { cookies } from "next/headers";

import { authApi, LoginDtoSchema } from "../server/auth";
import { redirect } from "next/navigation";

export type LoginActionState = {
  errors?: {
    email?: string;
    password?: string;
  };
  message?: string;
};

export async function login(formData: FormData): Promise<LoginActionState> {
  const payload = Object.fromEntries(formData.entries());
  const validationResult = LoginDtoSchema.safeParse(payload);

  if (!validationResult.success) {
    const issues = validationResult.error.issues;
    return {
      errors: {
        email: issues.find((issue) => issue.path[0] === "email")?.message,
        password: issues.find((issue) => issue.path[0] === "password")?.message,
      },
    };
  }

  const response = await authApi.login(validationResult.data);
  if (response.ok) {
    const {
      user,
      access_token,
    }: {
      access_token: string;
      user: {
        id: string;
        name: string;
        email: string;
      };
    } = await response.json();

    const cookieStore = await cookies();
    cookieStore.set("access_token", access_token);
    cookieStore.set("name", user.name);
    cookieStore.set("email", user.email);

    redirect("/");
  }

  if (response.status === 401) {
    return {
      message: "Invalid credentials",
    };
  }

  return {
    message: "An error occurred",
  };
}
