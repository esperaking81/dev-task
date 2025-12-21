import { TerminalSquareIcon } from "lucide-react";

export const Logo = ({
  color = "var(--primary)",
  size = 24,
}: {
  color?: string;
  size?: number;
}) => (
  <div className="rounded-sm bg-primary/10 p-2">
    <TerminalSquareIcon size={size} color={color} />
  </div>
);
