import { cn } from "@/lib/utils";

export const StatusChip = ({ status }: { status: string }) => {
  return (
    <div
      data-status={status}
      className={cn(
        "flex gap-2 border rounded-full",
        "px-4 py-1 text-xs font-bold",
        status === "TODO" &&
          "bg-surface-dark/20 border-surface-dark text-text-secondary",
        status === "IN_PROGRESS" &&
          "bg-blue/20 border-blue-500/30 text-blue-500",
        status === "DONE" &&
          "bg-surface-dark/20 border-green-500/30 text-green-500",
      )}
    >
      <span>{status === "DONE" ? "✔︎" : "•"}</span>
      <span>{status}</span>
    </div>
  );
};
