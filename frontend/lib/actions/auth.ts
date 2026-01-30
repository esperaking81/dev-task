"use server";

import { cookies } from "next/headers";

import { authApi, LoginDtoSchema } from "../server/auth";
import { redirect } from "next/navigation";
import { ApiError } from "../types/ApiError";

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

  try {
    const {
      user,
      maxAge,
      access_token,
    }: {
      access_token: string;
      user: {
        id: string;
        name: string;
        email: string;
      };
      maxAge: number;
    } = await authApi.login(validationResult.data);

    const cookieStore = await cookies();
    cookieStore.set("httpOnly", "true");
    cookieStore.set("name", user.name);
    cookieStore.set("email", user.email);
    cookieStore.set("maxAge", maxAge.toString());
    cookieStore.set("access_token", access_token);

    redirect("/");
  } catch (e) {
    if (e instanceof ApiError && e.status === 401) {
      return { message: "Invalid credentials." };
    }

    return { message: "Something went wrong." };
  }
}
