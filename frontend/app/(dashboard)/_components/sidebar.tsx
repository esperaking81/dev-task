"use client";

import React from "react";

import { NavLink } from "./ui/nav-link";
import { NAV_ITEMS } from "@/lib/constants";
import { SidebarFooter } from "./ui/sidebar-footer";
import { SidebarHeader } from "./ui/sidebar-header";

interface SidebarProps {
  onNewTaskClick?: () => void;
}

const Sidebar: React.FC<SidebarProps> = () => {
  return (
    <aside className="w-64 shrink-0 border-r border-border-dark bg-background-dark font-sans hidden md:flex flex-col">
      <div className="flex h-full flex-col justify-between p-4">
        <div className="flex flex-col gap-4">
          <SidebarHeader />

          <nav className="flex flex-col gap-1">
            {NAV_ITEMS.map((item) => (
              <NavLink key={item.path} item={item} />
            ))}
          </nav>
        </div>

        <SidebarFooter />
      </div>
    </aside>
  );
};

export default Sidebar;
