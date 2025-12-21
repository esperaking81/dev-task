"use client";

import { TerminalSquareIcon } from "lucide-react";

export const SidebarHeader = () => {
  return (
    <div className="px-3 py-2">
      <h1 className="text-white text-xl font-bold leading-normal tracking-tight flex items-center gap-2">
        <span className="text-primary">
          <TerminalSquareIcon />
        </span>
        DevTask
      </h1>
    </div>
  );
};
