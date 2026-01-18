import Sidebar from "./_components/sidebar";
import type { Metadata } from "next";

export const metadata: Metadata = {
  title: "DevTask AI | Dashboard",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <div
      className={`flex bg-background-dark h-screen w-full overflow-hidden font-sans`}
    >
      <Sidebar />
      <div className="flex-1 flex-col min-w-0">
        <main className="flex-1 overflow-y-auto overflow-x-hidden">
          {children}
        </main>
      </div>
    </div>
  );
}
