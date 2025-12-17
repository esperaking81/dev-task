import type { Metadata } from "next";
import { Work_Sans, JetBrains_Mono } from "next/font/google";
import "./globals.css";

const workSans = Work_Sans({
  variable: "--font-work-sans",
  subsets: ["latin"],
});

const jetbrainsMono = JetBrains_Mono({
  variable: "--font-jetbrains-mono",
  subsets: ["latin"],
});

export const metadata: Metadata = {
  title: "DevTask",
  description: "AI-powered task management for developers",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en" suppressHydrationWarning>
      <body
        className={`${workSans.variable} ${jetbrainsMono.variable} antialiased bg-neutral-50 dark:bg-neutral-900 text-neutral-900 dark:text-neutral-50 font-sans`}
        suppressHydrationWarning
      >
        {children}
      </body>
    </html>
  );
}
