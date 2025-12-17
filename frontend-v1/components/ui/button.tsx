import * as React from "react"
import { cn } from "@/lib/utils"

// Note: I am creating a simplified Button without Radix Slot if dependencies aren't fully here, 
// but standard shadcn-like button uses cva. 
// Wait, I didn't install class-variance-authority or @radix-ui/react-slot.
// I should add them or write a simpler button for now.
// Given the prompt didn't ask for shadcn explicitly but "clean", I'll stick to a simpler implementation 
// or I should install cva. It's standard. I'll stick to simple props for now to avoid dependency hell if install is stuck.

interface ButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement> {
    variant?: 'primary' | 'secondary' | 'ghost' | 'destructive' | 'outline' | 'ai';
    size?: 'sm' | 'md' | 'lg' | 'icon';
}

const Button = React.forwardRef<HTMLButtonElement, ButtonProps>(
    ({ className, variant = "primary", size = "md", ...props }, ref) => {

        const baseStyles = "inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-white transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-primary-500 focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50"

        const variants = {
            primary: "bg-primary-600 text-white hover:bg-primary-700 dark:bg-primary-600 dark:hover:bg-primary-700",
            secondary: "bg-neutral-100 text-neutral-900 hover:bg-neutral-200 dark:bg-neutral-800 dark:text-neutral-50 dark:hover:bg-neutral-700",
            ghost: "hover:bg-neutral-100 hover:text-neutral-900 dark:hover:bg-neutral-800 dark:hover:text-neutral-50",
            destructive: "bg-red-500 text-neutral-50 hover:bg-red-600 dark:bg-red-900 dark:text-neutral-50 dark:hover:bg-red-800",
            outline: "border border-neutral-200 bg-white hover:bg-neutral-100 hover:text-neutral-900 dark:border-neutral-800 dark:bg-neutral-950 dark:hover:bg-neutral-800 dark:hover:text-neutral-50",
            ai: "bg-gradient-to-r from-indigo-500 to-purple-500 text-white hover:from-indigo-600 hover:to-purple-600 border-0 shadow-md hover:shadow-lg",
        }

        const sizes = {
            sm: "h-9 rounded-md px-3",
            md: "h-10 rounded-md px-4 py-2",
            lg: "h-11 rounded-md px-8",
            icon: "h-10 w-10",
        }

        return (
            <button
                className={cn(baseStyles, variants[variant], sizes[size], className)}
                ref={ref}
                {...props}
            />
        )
    }
)
Button.displayName = "Button"

export { Button }
