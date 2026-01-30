import { cn } from "@/lib/utils";

export const PriorityChip = ({ priority }: { priority: string }) => {
  return (
    <div
      data-priority={priority}
      className={cn(
        "px-4 py-1 border rounded-md  uppercase",
        priority === "HIGH" && "bg-red-500/20 border-red-500/30 text-red-500",
        priority === "MEDIUM" &&
          "bg-amber-500/20 border-amber-500/30 text-amber-500",
        priority === "LOW" &&
          "bg-green-500/20 border-green-500/30 text-green-500",
      )}
    >
      {priority}
    </div>
  );
};
