const API_URL = process.env.NEXT_PUBLIC_API_URL;

export async function fetcher(endpoint: string, options?: RequestInit) {
  const headers = new Headers(options?.headers);
  headers.set("Content-Type", "application/json");

  // For client-side requests, cookies are automatically included
  return fetch(`${API_URL}${endpoint}`, {
    ...options,
    credentials: "include",
    headers,
  });
}