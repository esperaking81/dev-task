import { Space_Grotesk } from "next/font/google";
import type { Metadata } from "next";
import "./globals.css";
import { Toaster } from "sonner";

const spaceSans = Space_Grotesk({
  variable: "--font-space-grotesk",
  subsets: ["latin"],
});

export const metadata: Metadata = {
  title: "DevTask AI",
  description: "Manage tasks. Automate breakdown",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body
        className={`${spaceSans.variable} bg-background text-white antialiased`}
      >
        {children}
        <Toaster position="top-right" />
      </body>
    </html>
  );
}
