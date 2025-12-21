import { NextRequest, NextResponse } from "next/server";

export default function proxy(request: NextRequest) {
  const token = request.cookies.get("access_token");
  const { pathname } = request.nextUrl;

  // Allow access to login and signup pages
  if (pathname === "/login" || pathname === "/signup") {
    // If user is logged in and trying to access login, redirect to dashboard
    if (token) {
      return NextResponse.redirect(new URL("/", request.url));
    }
    // Allow access to login/signup if not logged in
    return NextResponse.next();
  }

  // For all other routes, require authentication
  if (!token) {
    return NextResponse.redirect(new URL("/login", request.url));
  }

  // User is authenticated, allow access
  return NextResponse.next();
}

export const config = {
  matcher: [
    /*
     * Match all request paths except for the ones starting with:
     * - api (API routes)
     * - _next/static (static files)
     * - _next/image (image optimization files)
     * - favicon.ico (favicon file)
     * - public files with extensions
     */
    "/((?!api|_next/static|_next/image|favicon.ico|.*\\.).*)",
  ],
};
