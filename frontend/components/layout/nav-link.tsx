import { NAV_ITEMS } from "@/lib/constants";
import clsx from "clsx";
import Link from "next/link";
import { usePathname } from "next/navigation";

export const NavLink: React.FC<{ item: (typeof NAV_ITEMS)[number] }> = ({
  item,
}) => {
  const isActive = usePathname() === item.path;
  return (
    <Link
      key={item.label}
      href={item.path}
      className={clsx(
        "flex items-center gap-3 px-3 py-2 rounded-lg transition-colors group",
        isActive && "bg-surface-dark text-white",
        !isActive &&
          "text-text-secondary hover:bg-surface-dark/50 hover:text-white",
      )}
    >
      <item.icon className="w-4 h-4 text-primary" />
      <p className="text-sm font-medium">{item.label}</p>
    </Link>
  );
};
