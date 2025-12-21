import { cookies } from "next/headers";

const API_URL = process.env.NEXT_PUBLIC_API_URL;

export async function serverFetcher(endpoint: string, options?: RequestInit) {
  const headers = new Headers(options?.headers);
  headers.set("Content-Type", "application/json");

  // Server-side: manually add cookie from next/headers
  const cookieStore = await cookies();
  const token = cookieStore.get("access_token")?.value;

  if (token) {
    headers.set("Cookie", `access_token=${token}`);
  }

  return fetch(`${API_URL}${endpoint}`, {
    ...options,
    headers,
  });
}