"use client"

import { useState, useEffect } from "react"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { TaskList } from "@/components/task/TaskList"
import { TaskDetail } from "@/components/task/TaskDetail"
import { CreateTaskModal } from "@/components/task/CreateTaskModal"
import { Task } from "@/types"
import { api } from "@/lib/api"
import { Plus, Search, Filter } from "lucide-react"

export default function Dashboard() {
  const [tasks, setTasks] = useState<Task[]>([])
  const [searchQuery, setSearchQuery] = useState("")
  const [isLoading, setIsLoading] = useState(true)
  const [selectedTask, setSelectedTask] = useState<Task | null>(null)
  const [isDetailOpen, setIsDetailOpen] = useState(false)
  const [isCreateOpen, setIsCreateOpen] = useState(false)

  const [error, setError] = useState<string | null>(null)

  // Initial Fetch & Auth
  useEffect(() => {
    const init = async () => {
      try {
        // Auto-login for MVP Demo
        const authData = await api.auth.login("dev@devtask.com", "password123")
        if (!authData.access_token) {
          throw new Error("No access_token in login response: " + JSON.stringify(authData))
        }
        api.setToken(authData.access_token)

        // Fetch Tasks (Task 1 has subtasks usually)
        const fetchedTasks = await api.tasks.getAll()
        setTasks(fetchedTasks)
        setError(null)
      } catch (err: any) {
        console.error("Failed to init", err)
        setError(err.message || String(err))
      } finally {
        setIsLoading(false)
      }
    }
    init()
  }, [])

  const handleCreateTask = async (taskData: any) => {
    const newTask = await api.tasks.create(taskData)
    setTasks(prev => [newTask, ...prev])
    setIsCreateOpen(false)
  }

  const handleDeleteTask = async (taskId: string) => {
    if (!window.confirm("Are you sure you want to delete this task?")) return

    try {
      await api.tasks.delete(taskId)
      setTasks(prev => prev.filter(t => t.id !== taskId))
      if (selectedTask?.id === taskId) {
        setIsDetailOpen(false)
        setSelectedTask(null)
      }
    } catch (err) {
      console.error("Failed to delete task", err)
      setError("Failed to delete task")
    }
  }

  const filteredTasks = tasks.filter(t =>
    t.title.toLowerCase().includes(searchQuery.toLowerCase()) ||
    t.tags.some(tag => tag.name.toLowerCase().includes(searchQuery.toLowerCase()))
  )

  const handleTaskClick = (task: Task) => {
    setSelectedTask(task)
    setIsDetailOpen(true)
  }

  const handleTaskUpdate = (updatedTask: Task) => {
    setTasks(prev => prev.map(t => t.id === updatedTask.id ? updatedTask : t))
    setSelectedTask(updatedTask)
  }

  return (
    <div className="min-h-screen bg-neutral-50 dark:bg-neutral-900 pb-20">
      {/* Header */}
      <header className="border-b border-neutral-200 dark:border-neutral-800 bg-white dark:bg-neutral-950 sticky top-0 z-10">
        <div className="max-w-4xl mx-auto px-4 h-16 flex items-center justify-between">
          <div className="flex items-center gap-2">
            <div className="h-8 w-8 bg-primary-600 rounded-lg flex items-center justify-center text-white font-bold">DT</div>
            <h1 className="text-lg font-semibold tracking-tight">DevTask</h1>
          </div>
          <div className="flex items-center gap-2">
            <div className="h-8 w-8 rounded-full bg-neutral-200 dark:bg-neutral-800" />
          </div>
        </div>
      </header>

      {/* Debug Info */}
      {error && (
        <div className="max-w-4xl mx-auto px-4 mt-4">
          <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative" role="alert">
            <strong className="font-bold">Error:</strong>
            <span className="block sm:inline"> {error}</span>
          </div>
        </div>
      )}

      {/* Main Content */}
      <main className="max-w-4xl mx-auto px-4 py-8 space-y-8">

        {/* Actions Bar */}
        <div className="flex flex-col sm:flex-row gap-4 justify-between items-start sm:items-center">
          <div>
            <h2 className="text-3xl font-bold tracking-tight">My Tasks</h2>
            <p className="text-neutral-500">Manage your development workflow</p>
          </div>
          <Button onClick={() => setIsCreateOpen(true)}>
            <Plus className="mr-2 h-4 w-4" /> New Task
          </Button>
        </div>

        {/* Filters */}
        <div className="flex gap-2 items-center">
          <div className="relative flex-1 max-w-sm">
            <Search className="absolute left-2.5 top-2.5 h-4 w-4 text-neutral-500" />
            <Input
              placeholder="Search tasks..."
              className="pl-9"
              value={searchQuery}
              onChange={(e: React.ChangeEvent<HTMLInputElement>) => setSearchQuery(e.target.value)}
            />
          </div>
          <Button variant="outline" size="icon">
            <Filter className="h-4 w-4" />
          </Button>
        </div>

        {/* Task List */}
        {isLoading ? (
          <div className="space-y-4">
            {[1, 2, 3].map(i => (
              <div key={i} className="h-24 bg-white dark:bg-neutral-800 rounded-lg border border-neutral-200 dark:border-neutral-800 animate-pulse" />
            ))}
          </div>
        ) : (
          <TaskList
            tasks={filteredTasks}
            onTaskClick={handleTaskClick}
            onTaskDelete={handleDeleteTask}
          />
        )}

      </main>

      <TaskDetail
        task={selectedTask}
        isOpen={isDetailOpen}
        onClose={() => setIsDetailOpen(false)}
        onUpdate={handleTaskUpdate}
      />

      <CreateTaskModal
        isOpen={isCreateOpen}
        onClose={() => setIsCreateOpen(false)}
        onCreate={handleCreateTask}
      />
    </div>
  )
}
