import { cookies } from "next/headers";
import { redirect } from "next/navigation";
import { ApiError } from "../types/ApiError";

const API_URL = process.env.API_URL;

type ServerFetcherOptions = RequestInit & {
  skipAuthRedirect?: boolean;
};

export async function serverFetcher<T>(
  endpoint: string,
  options?: ServerFetcherOptions,
): Promise<T> {
  const { skipAuthRedirect, ...fetchOptions } = options || {};
  const headers = new Headers(fetchOptions?.headers);
  headers.set("Content-Type", "application/json");

  // Server-side: manually add cookie from next/headers
  const cookieStore = await cookies();
  const token = cookieStore.get("access_token")?.value;

  if (token) {
    headers.set("Authorization", `Bearer ${token}`);
  }

  const response = await fetch(`${API_URL}${endpoint}`, {
    ...fetchOptions,
    headers,
    credentials: "include",
  });

  if (response.status === 401) {
    if (!skipAuthRedirect) {
      redirect("/login");
    }

    const error = await response.json().catch(() => {});

    throw new ApiError(401, "Unauthorized", error);
  }

  return response.json() as Promise<T>;
}
