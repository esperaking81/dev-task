import * as React from "react"
import { Task } from "@/types"
import { Badge } from "@/components/ui/badge"
import { Button } from "@/components/ui/button"
import { Card, CardContent } from "@/components/ui/card"
import { CheckCircle2, Circle, Clock, Trash2, Sparkles, ListTodo } from "lucide-react"
import { cn } from "@/lib/utils"

interface TaskItemProps {
    task: Task
    onClick?: () => void
    onDelete?: () => void
}

export function TaskItem({ task, onClick, onDelete }: TaskItemProps) {
    const completedSubtasks = task.subtasks.filter(st => st.completed).length
    const totalSubtasks = task.subtasks.length

    const statusIcon = {
        TODO: <Circle className="h-5 w-5 text-neutral-400" />,
        IN_PROGRESS: <Clock className="h-5 w-5 text-amber-500" />,
        DONE: <CheckCircle2 className="h-5 w-5 text-green-500" />,
    }

    return (
        <Card
            className={cn(
                "group cursor-pointer transition-all hover:shadow-md hover:border-primary-200 dark:hover:border-primary-800",
                task.status === "DONE" && "opacity-60"
            )}
            onClick={onClick}
        >
            <CardContent className="p-4 flex items-start gap-4">
                <div className="mt-1">
                    {statusIcon[task.status]}
                </div>

                <div className="flex-1 space-y-2">
                    <div className="flex justify-between items-start">
                        <h3 className={cn("font-medium text-base", task.status === "DONE" && "line-through decoration-neutral-400")}>
                            {task.title}
                        </h3>
                        <Button
                            variant="ghost"
                            size="icon"
                            className="opacity-0 group-hover:opacity-100 h-8 w-8 hover:bg-red-50 hover:text-red-600 dark:hover:bg-red-950/30"
                            onClick={(e) => {
                                e.stopPropagation()
                                onDelete?.()
                            }}
                        >
                            <Trash2 className="h-4 w-4" />
                        </Button>
                    </div>

                    <div className="flex flex-wrap gap-2 items-center text-xs text-neutral-500">
                        {task.tags.map(tag => (
                            <Badge
                                key={tag.id}
                                className="border-transparent"
                                style={{
                                    backgroundColor: tag.color ? `${tag.color}20` : undefined,
                                    color: tag.color,
                                }}
                            >
                                {tag.name}
                            </Badge>
                        ))}

                        {(totalSubtasks > 0) && (
                            <div className="flex items-center gap-1 ml-auto">
                                <ListTodo className="h-3.5 w-3.5" />
                                <span>{completedSubtasks}/{totalSubtasks}</span>
                            </div>
                        )}

                        {/* AI Hint - shows if task needs breakdown or assistance */}
                        {task.status === 'TODO' && totalSubtasks === 0 && (
                            <div className="hidden group-hover:flex items-center gap-1 text-primary-600 dark:text-primary-400 ml-2 animate-in fade-in">
                                <Sparkles className="h-3 w-3" />
                                <span>AI Configurable</span>
                            </div>
                        )}
                    </div>
                </div>
            </CardContent>
        </Card>
    )
}
