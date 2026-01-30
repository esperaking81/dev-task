"use client";

import { Task } from "@/lib/types/task";
import { ColumnDef } from "@tanstack/react-table";
import { format } from "date-fns";
import { Calendar } from "lucide-react";
import { Checkbox } from "../ui/checkbox";
import { PriorityChip } from "./priority-chip";
import { StatusChip } from "./status-chip";

export const columns: ColumnDef<Task>[] = [
  {
    id: "select",
    header: ({ table }) => (
      <Checkbox
        checked={
          table.getIsAllPageRowsSelected() ||
          (table.getIsSomePageRowsSelected() && "indeterminate")
        }
        onCheckedChange={(value) => table.toggleAllPageRowsSelected(!!value)}
        aria-label="Select all"
      />
    ),
    cell: ({ row }) => (
      <Checkbox
        checked={row.getIsSelected()}
        onCheckedChange={(value) => row.toggleSelected(!!value)}
        aria-label="Select row"
      />
    ),
  },
  {
    accessorKey: "title",
    header: "TASK NAME",
  },
  {
    accessorKey: "priority",
    header: "PRIORITY",
    cell: ({ row }) => (
      <div className="flex">
        <PriorityChip priority={row.getValue("priority")} />
      </div>
    ),
  },
  {
    header: "ASSIGNEE",
    accessorFn: (task) =>
      task.assignees.map((user) => user.firstName).join(", "),
  },
  {
    header: "DUE DATE",
    accessorKey: "dueDate",
    cell: ({ row }) =>
      row.getValue("dueDate") ? (
        <div className="flex items-center gap-2">
          <Calendar size={16} />
          <span>{format(new Date(row.getValue("dueDate")), "MMM dd")}</span>
        </div>
      ) : (
        "—"
      ),
  },
  {
    accessorKey: "status",
    header: "STATUS",
    cell: ({ row }) => (
      <div className="flex">
        <StatusChip status={row.getValue("status")} />
      </div>
    ),
  },
];
