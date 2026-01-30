import {
  InputGroup,
  InputGroupAddon,
  InputGroupInput,
} from "@/components/ui/input-group";

import Image from "next/image";
import avatarIcon from "../../public/avatar.svg";

import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";

import { Button } from "@/components/ui/button";
import { Separator } from "@/components/ui/separator";
import {
  BellDotIcon,
  ChevronDown,
  Columns4,
  List,
  ListFilter,
  Search,
  SquareKanban,
} from "lucide-react";
import { TasksList } from "@/components/tasks/TasksList";

export default function Page() {
  return (
    <div className="flex flex-col min-h-screen">
      <Header />
      <Dashboard />
    </div>
  );
}

const Header = () => {
  return (
    <div className="w-full flex p-4 border-b border-border-dark">
      <div className="flex-1">
        <InputGroup className="w-1/2 border-none">
          <InputGroupInput placeholder="Search tasks..." />
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

const Dashboard = () => {
  return (
    <Tabs defaultValue="list" className="flex flex-col p-4 gap-6">
      <Heading />
      <ActionBar />
      <Tasks />
    </Tabs>
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

const ActionBar = () => {
  return (
    <div className="flex justify-between w-full">
      <div className="flex items-center gap-2">
        <FilterButton label="Status" />
        <FilterButton label="Due Date" />
        <FilterButton label="Assignee" />
        <Separator orientation="vertical" className="bg-border-dark mx-2" />
        <Button className="font-bold text-primary" variant="ghost">
          <ListFilter />
          {"More Filters"}
        </Button>
      </div>
      <div>
        <ViewModeTabs />
      </div>
    </div>
  );
};

const FilterButton = ({ label = "Filter" }: { label: string }) => {
  return (
    <Button className="bg-surface-dark font-bold border border-white/5">
      {label}
      <ChevronDown className="text-text-secondary" />
    </Button>
  );
};

const ViewModeTabs = () => {
  return (
    <TabsList>
      <TabsTrigger value="list">
        <List />
        List
      </TabsTrigger>
      <TabsTrigger value="board">
        <SquareKanban /> Board
      </TabsTrigger>
      <TabsTrigger value="timeline">
        <Columns4 />
        Timeline
      </TabsTrigger>
    </TabsList>
  );
};

const Tasks = () => {
  return (
    <>
      <TabsContent value="list">
        <TasksList />
      </TabsContent>
      <TabsContent value="board">tasks board</TabsContent>
      <TabsContent value="timeline">tasks timeline</TabsContent>
    </>
  );
};
