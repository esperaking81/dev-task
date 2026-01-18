import {
  InputGroup,
  InputGroupInput,
  InputGroupAddon,
} from "@/components/ui/input-group";

import Image from "next/image";
import avatarIcon from "../../public/avatar.svg";

import { Search, BellDotIcon } from "lucide-react";

export default function Page() {
  return (
    <div className="flex flex-col min-h-screen">
      <Header />
      <DashboardTasks />
    </div>
  );
}

const Header = () => {
  return (
    <div className="w-full flex p-4 border-b border-border-dark">
      <div className="flex-1">
        <InputGroup className="w-1/2">
          <InputGroupInput
            className="border-none bg-none bg-background-dark"
            placeholder="Search tasks..."
          />
          <InputGroupAddon>
            <Search />
          </InputGroupAddon>
          <InputGroupAddon align="inline-end">
            <kbd className="bg-muted text-muted-foreground pointer-events-none inline-flex h-5 items-center gap-1 rounded border px-1.5 font-mono text-[10px] font-medium opacity-100 select-none">
              <span className="text-xs">⌘</span>K
            </kbd>
          </InputGroupAddon>
        </InputGroup>
      </div>

      <div className="flex items-center gap-4">
        <div className="cursor-pointer">
          <BellDotIcon size={20} />
        </div>
        <div className="cursor-pointer">
          <Image src={avatarIcon} alt="avatar" width={32} height={32} />
        </div>
      </div>
    </div>
  );
};

const DashboardTasks = () => {
  return (
    <div className="p-4">
      <Heading />
    </div>
  );
};

const Heading = () => {
  return (
    <div>
      <h3 className="text-4xl font-bold">Good morning, Alex</h3>
      <div className="flex w-full text-text-secondary ">
        <span className="flex-1">
          You have <b className="text-white">5 tasks</b> pending review today.
        </span>
        <span>Tuesday, Oct 24</span>
      </div>
    </div>
  );
};
