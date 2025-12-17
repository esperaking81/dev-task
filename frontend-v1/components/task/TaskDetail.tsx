import * as React from "react"
import { ExternalLink, Sparkles, Loader2, CheckSquare, Square } from "lucide-react"
import { Modal } from "@/components/ui/modal"
import { Button } from "@/components/ui/button"
import { Badge } from "@/components/ui/badge"
import { Task } from "@/types"
import { api } from "@/lib/api"
import { cn } from "@/lib/utils"

interface TaskDetailProps {
    task: Task | null
    isOpen: boolean
    onClose: () => void
    onUpdate: (updatedTask: Task) => void
}

export function TaskDetail({ task, isOpen, onClose, onUpdate }: TaskDetailProps) {
    const [isAiLoading, setIsAiLoading] = React.useState(false)
    const [localTask, setLocalTask] = React.useState<Task | null>(task)

    React.useEffect(() => {
        setLocalTask(task)
    }, [task])

    if (!localTask) return null

    const handleAiBreakdown = async () => {
        setIsAiLoading(true)
        try {
            const brokenDownTask = await api.tasks.breakDown(localTask.id)
            setLocalTask(brokenDownTask)
            onUpdate(brokenDownTask)
        } catch (error) {
            console.error("AI Breakdown failed", error)
        } finally {
            setIsAiLoading(false)
        }
    }

    const toggleSubtask = (subtaskId: string) => {
        if (!localTask) return
        const updatedSubtasks = localTask.subtasks.map(st =>
            st.id === subtaskId ? { ...st, completed: !st.completed } : st
        )
        const updated = { ...localTask, subtasks: updatedSubtasks }
        setLocalTask(updated)
        onUpdate(updated)
    }

    return (
        <Modal isOpen={isOpen} onClose={onClose} title="Task Details">
            <div className="space-y-6">
                {/* Header Info */}
                <div className="space-y-4">
                    <div>
                        <h3 className="text-sm font-medium text-neutral-500 uppercase tracking-wider mb-1">Title</h3>
                        <div className="text-lg font-medium">{localTask.title}</div>
                    </div>

                    <div className="flex gap-2">
                        <Badge variant={localTask.status === "DONE" ? "default" : "secondary"}>
                            {localTask.status.replace("_", " ")}
                        </Badge>
                        {localTask.tags.map(tag => (
                            <Badge
                                key={tag.id}
                                variant="outline"
                                className="border-transparent"
                                style={{
                                    backgroundColor: tag.color ? `${tag.color}20` : undefined,
                                    color: tag.color,
                                }}
                            >
                                {tag.name}
                            </Badge>
                        ))}
                    </div>

                    <div>
                        <h3 className="text-sm font-medium text-neutral-500 uppercase tracking-wider mb-2">Description</h3>
                        <div className="p-4 bg-neutral-50 dark:bg-neutral-800 rounded-md text-sm leading-relaxed whitespace-pre-wrap">
                            {localTask.description}
                        </div>
                    </div>
                </div>

                <div className="border-t border-neutral-200 dark:border-neutral-800 my-4" />

                {/* AI Section */}
                <div className="bg-indigo-50 dark:bg-indigo-950/30 border border-indigo-100 dark:border-indigo-900 rounded-lg p-5">
                    <div className="flex items-center justify-between mb-4">
                        <div className="flex items-center gap-2">
                            <Sparkles className="h-4 w-4 text-indigo-600 dark:text-indigo-400" />
                            <h3 className="font-semibold text-indigo-900 dark:text-indigo-200">AI Assistant</h3>
                        </div>

                        {!isAiLoading ? (
                            <Button variant="ai" size="sm" onClick={handleAiBreakdown} disabled={localTask.subtasks.length > 3}>
                                Break down task
                            </Button>
                        ) : (
                            <Button variant="ai" size="sm" disabled>
                                <Loader2 className="mr-2 h-4 w-4 animate-spin" />
                                Thinking...
                            </Button>
                        )}
                    </div>

                    {isAiLoading && (
                        <div className="space-y-2 animate-pulse">
                            <div className="h-4 bg-indigo-200/50 rounded w-3/4"></div>
                            <div className="h-4 bg-indigo-200/50 rounded w-1/2"></div>
                        </div>
                    )}
                </div>

                {/* Subtasks */}
                <div className="space-y-3">
                    <h3 className="text-sm font-medium text-neutral-500 uppercase tracking-wider">Subtasks ({localTask.subtasks.filter(t => t.completed).length}/{localTask.subtasks.length})</h3>

                    {localTask.subtasks.length === 0 ? (
                        <div className="text-center py-6 text-neutral-400 text-sm border-2 border-dashed border-neutral-100 rounded-lg">
                            No subtasks yet. Use AI to generate them!
                        </div>
                    ) : (
                        <div className="space-y-2">
                            {localTask.subtasks.map(st => (
                                <div key={st.id}
                                    className="flex items-center gap-3 p-3 bg-white dark:bg-neutral-800 border border-neutral-100 dark:border-neutral-700 rounded-md hover:border-primary-200 transition-colors cursor-pointer group"
                                    onClick={() => toggleSubtask(st.id)}
                                >
                                    {st.completed ? (
                                        <CheckSquare className="h-4 w-4 text-primary-600" />
                                    ) : (
                                        <Square className="h-4 w-4 text-neutral-400 group-hover:text-primary-600" />
                                    )}
                                    <span className={cn("text-sm", st.completed && "line-through text-neutral-400")}>{st.title}</span>
                                </div>
                            ))}
                        </div>
                    )}
                </div>

                <div className="flex justify-end gap-2 pt-4">
                    <Button variant="ghost" onClick={onClose}>Close</Button>
                    <Button onClick={onClose}>Save Changes</Button>
                </div>

            </div>
        </Modal>
    )
}
