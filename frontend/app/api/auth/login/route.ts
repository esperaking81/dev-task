import { LoginDtoSchema } from "@/lib/server/auth";

export async function POST(request: Request) {
  const body = await request.json();

  const validationResult = LoginDtoSchema.safeParse(body);

  if (!validationResult.success) {
    const issues = validationResult.error.issues;
    return Response.json(
      {
        errors: {
          email: issues.find((issue) => issue.path[0] === "email")?.message,
          password: issues.find((issue) => issue.path[0] === "password")
            ?.message,
        },
      },
      { status: 400 },
    );
  }

  const response = await fetch(`${process.env.API_URL}/auth/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(body),
  });

  if (!response.ok) {
    return Response.json({ error: "Login failed" }, { status: 401 });
  }

  const data = await response.json();

  const setCookieHeader = response.headers.get("set-cookie");

  const headers = new Headers();
  if (setCookieHeader) {
    headers.set("set-cookie", setCookieHeader);
  }

  return Response.json(data, { headers });
}
