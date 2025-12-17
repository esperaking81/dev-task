import { Task } from "@/types"
import { TaskItem } from "./TaskItem"

interface TaskListProps {
    tasks: Task[]
    onTaskClick: (task: Task) => void
    onTaskDelete: (taskId: string) => void
}

export function TaskList({ tasks, onTaskClick, onTaskDelete }: TaskListProps) {
    if (tasks.length === 0) {
        return (
            <div className="text-center py-12 text-neutral-500">
                <p>No tasks found. Create one to get started!</p>
            </div>
        )
    }

    return (
        <div className="space-y-3">
            {tasks.map((task) => (
                <TaskItem
                    key={task.id}
                    task={task}
                    onClick={() => onTaskClick(task)}
                    onDelete={() => onTaskDelete(task.id)}
                />
            ))}
        </div>
    )
}
