import * as React from "react"
import { cn } from "@/lib/utils"

export interface BadgeProps extends React.HTMLAttributes<HTMLDivElement> {
    variant?: "default" | "secondary" | "destructive" | "outline" | "backend" | "frontend" | "bug" | "design";
}

function Badge({ className, variant = "default", ...props }: BadgeProps) {
    const baseStyles = "inline-flex items-center rounded-full border px-2.5 py-0.5 text-xs font-semibold transition-colors focus:outline-none focus:ring-2 focus:ring-ring focus:ring-offset-2"

    const variants = {
        default: "border-transparent bg-primary-600 text-white hover:bg-primary-700",
        secondary: "border-transparent bg-neutral-100 text-neutral-900 hover:bg-neutral-200 dark:bg-neutral-800 dark:text-neutral-50",
        destructive: "border-transparent bg-red-500 text-white hover:bg-red-600",
        outline: "text-neutral-950 dark:text-neutral-50 border-neutral-200 dark:border-neutral-800",

        // Custom Tag Colors (using bg-opacity/text method from spec)
        backend: "border-transparent bg-blue-100 text-blue-700 dark:bg-blue-900/30 dark:text-blue-300",
        frontend: "border-transparent bg-green-100 text-green-700 dark:bg-green-900/30 dark:text-green-300",
        bug: "border-transparent bg-red-100 text-red-700 dark:bg-red-900/30 dark:text-red-300",
        design: "border-transparent bg-purple-100 text-purple-700 dark:bg-purple-900/30 dark:text-purple-300",
    }

    return (
        <div className={cn(baseStyles, variants[variant], className)} {...props} />
    )
}

export { Badge }
