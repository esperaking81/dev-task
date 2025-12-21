"use client";

import { PlusIcon } from "lucide-react";
import Image from "next/image";

export const SidebarFooter = () => {
  return (
    <div className="flex flex-col gap-4">
      <NewTaskButton />

      <div className="p-2 border-t border-border-dark pt-4">
        <div className="flex items-center gap-3">
          <Image
            className="w-8 h-8 rounded-full border border-border-dark"
            src="https://picsum.photos/seed/alex/100/100"
            height={100}
            width={100}
            alt="User"
          />
          <div className="flex flex-col min-w-0">
            <p className="text-sm font-medium text-white truncate">Alex D.</p>
            <p className="text-xs text-text-secondary">Pro Member</p>
          </div>
        </div>
      </div>
    </div>
  );
};

const NewTaskButton = ({ onNewTaskClick }: { onNewTaskClick?: () => void }) => (
  <button
    onClick={onNewTaskClick}
    className="flex w-full items-center justify-center overflow-hidden rounded-lg h-10 px-4 bg-primary hover:bg-blue-600 transition-colors text-white text-sm font-bold shadow-lg shadow-primary/20"
  >
    <span className="flex items-center gap-2">
      <span className="text-[20px]">
        <PlusIcon />
      </span>{" "}
      New Task
    </span>
  </button>
);
