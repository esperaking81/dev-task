// app/api/auth/logout/route.ts
import { cookies } from "next/headers";

export async function POST() {
  const cookieStore = await cookies();
  cookieStore.delete("access_token");

  // Optional: notify NestJS backend
  await fetch(`${process.env.API_URL}/auth/logout`, {
    method: "POST",
    credentials: "include",
    headers: { Cookie: cookieStore.toString() },
  });

  return Response.json({ success: true });
}
