import Image from "next/image";
import { LucideListTree, LucideSparkles } from "lucide-react";
import { authLayoutData } from "@/lib/constants";

export default function Layout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <div className="min-h-screen w-full flex bg-[#0b0e14] text-white font-sans">
      {/** Left Section: Login/Signup Form */}
      <div className="bg-background-dark flex-1 flex px-4 sm:px-6 lg:px-20 xl:px-24 justify-center">
        <div className="mx-auto w-full max-w-sm lg:w-96">{children}</div>
      </div>

      {/** Right Section: Branding */}
      <div className="hidden lg:flex relative w-0 flex-1 bg-[#0a0d14]">
        <Image
          className="absolute inset-0 z-0 h-full w-full opacity-80"
          src={authLayoutData.image.login.url}
          alt={authLayoutData.image.login.alt}
          fill={true}
        />
        <div className="absolute inset-0 bg-linear-to-t from-[#101622] via-[#101622]/40 to-transparent z-10"></div>
        <div className="relative z-20 flex flex-col justify-end p-20 w-full h-full pb-32">
          <div className="max-w-lg">
            <div className="inline-flex items-center gap-2 px-3 py-1 rounded-full bg-primary/20 border border-primary/30 text-primary text-xs font-bold uppercase tracking-wider mb-6 backdrop-blur-sm">
              <span className="text-[10px]">
                <LucideSparkles size={16} />
              </span>
              <span>AI-Powered Workflow</span>
            </div>
            <h2 className="text-4xl font-bold tracking-tight text-white sm:text-5xl mb-6">
              Break down complexity. <br /> Build faster.
            </h2>
            <p className="text-lg leading-relaxed text-gray-300">
              DevTask uses advanced AI to decompose your user stories into
              actionable subtasks automatically. Spend less time planning and
              more time shipping.
            </p>
            {/* <!-- Feature Preview / Fake UI Element --> */}
            <div className="mt-10 p-4 rounded-xl bg-surface-dark/60 border border-border-dark backdrop-blur-md max-w-sm transform translate-x-10 translate-y-4 shadow-2xl">
              <div className="flex items-center gap-3 mb-3 border-b border-border-dark/50 pb-3">
                <div className="size-8 rounded bg-primary/20 flex items-center justify-center text-primary">
                  <LucideListTree />
                </div>
                <div>
                  <div className="text-xs text-text-secondary">
                    Generating subtasks...
                  </div>
                  <div className="text-sm font-medium text-white">
                    Feature: User Authentication
                  </div>
                </div>
              </div>
              <div className="space-y-2">
                <div className="h-2 w-3/4 bg-border-dark/50 rounded animate-pulse"></div>
                <div className="h-2 w-1/2 bg-border-dark/50 rounded animate-pulse delay-75"></div>
                <div className="h-2 w-5/6 bg-border-dark/50 rounded animate-pulse delay-150"></div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
