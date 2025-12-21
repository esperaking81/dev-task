"use client";

import * as React from "react";
import { useParams, useRouter } from "next/navigation";
import { ArrowLeft, Edit2, Trash2, Sparkles, Loader2, CheckSquare, Square, Calendar, User } from "lucide-react";
import { Button } from "@/components/ui/button";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Badge } from "@/components/ui/badge";
import { api } from "@/lib/api";
import { Task } from "@/lib/types/task";
import { cn } from "@/lib/utils";

export default function TaskDetailPage() {
  const params = useParams();
  const router = useRouter();
  const taskId = params.id as string;

  const [task, setTask] = React.useState<Task | null>(null);
  const [isLoading, setIsLoading] = React.useState(true);
  const [isAiLoading, setIsAiLoading] = React.useState(false);
  const [error, setError] = React.useState<string | null>(null);

  React.useEffect(() => {
    if (taskId) {
      fetchTask();
    }
  }, [taskId]);

  const fetchTask = async () => {
    try {
      setIsLoading(true);
      const taskData = await api.tasks.getById(taskId);
      setTask(taskData);
    } catch (err) {
      setError(err instanceof Error ? err.message : "Failed to fetch task");
    } finally {
      setIsLoading(false);
    }
  };

  const handleAiBreakdown = async () => {
    if (!task) return;
    
    setIsAiLoading(true);
    try {
      const brokenDownTask = await api.tasks.breakDown(task.id);
      setTask(brokenDownTask);
    } catch (error) {
      console.error("AI Breakdown failed", error);
      setError("Failed to break down task with AI");
    } finally {
      setIsAiLoading(false);
    }
  };

  const handleDelete = async () => {
    if (!task) return;
    
    if (confirm("Are you sure you want to delete this task?")) {
      try {
        await api.tasks.delete(task.id);
        router.push("/dashboard");
      } catch (error) {
        console.error("Delete failed", error);
        setError("Failed to delete task");
      }
    }
  };

  const toggleSubtask = async (subtaskId: string) => {
    if (!task) return;
    
    // This would need an API endpoint to toggle subtask completion
    // For now, we'll just update the local state
    const updatedSubtasks = task.subtasks?.map(st =>
      st.id === subtaskId ? { ...st, status: st.status === "DONE" ? "TODO" : "DONE" } : st
    ) || [];
    
    const updated = { ...task, subtasks: updatedSubtasks };
    setTask(updated);
  };

  const getStatusColor = (status: string) => {
    switch (status) {
      case "DONE":
        return "default";
      case "IN_PROGRESS":
        return "secondary";
      default:
        return "outline";
    }
  };

  const getPriorityColor = (priority: string) => {
    switch (priority) {
      case "HIGH":
        return "destructive";
      case "MEDIUM":
        return "secondary";
      default:
        return "outline";
    }
  };

  if (isLoading) {
    return (
      <div className="container mx-auto px-4 py-8">
        <div className="animate-pulse">
          <div className="h-8 bg-gray-200 rounded w-1/4 mb-4"></div>
          <div className="h-4 bg-gray-200 rounded w-1/2 mb-2"></div>
          <div className="h-4 bg-gray-200 rounded w-3/4"></div>
        </div>
      </div>
    );
  }

  if (error || !task) {
    return (
      <div className="container mx-auto px-4 py-8">
        <div className="text-center">
          <h1 className="text-2xl font-bold text-red-600 mb-4">Error</h1>
          <p className="text-gray-600 mb-4">{error || "Task not found"}</p>
          <Button onClick={() => router.push("/dashboard")}>
            Back to Dashboard
          </Button>
        </div>
      </div>
    );
  }

  const completedSubtasks = task.subtasks?.filter(st => st.status === "DONE").length || 0;
  const totalSubtasks = task.subtasks?.length || 0;

  return (
    <div className="container mx-auto px-4 py-8 max-w-4xl">
      {/* Header */}
      <div className="flex items-center justify-between mb-6">
        <div className="flex items-center gap-4">
          <Button
            variant="ghost"
            size="sm"
            onClick={() => router.back()}
            className="flex items-center gap-2"
          >
            <ArrowLeft className="h-4 w-4" />
            Back
          </Button>
          <h1 className="text-3xl font-bold">{task.title}</h1>
        </div>
        
        <div className="flex items-center gap-2">
          <Button variant="outline" size="sm">
            <Edit2 className="h-4 w-4 mr-2" />
            Edit
          </Button>
          <Button variant="destructive" size="sm" onClick={handleDelete}>
            <Trash2 className="h-4 w-4 mr-2" />
            Delete
          </Button>
        </div>
      </div>

      {/* Status and Priority Badges */}
      <div className="flex items-center gap-3 mb-6">
        <Badge variant={getStatusColor(task.status)}>
          {task.status.replace("_", " ")}
        </Badge>
        <Badge variant={getPriorityColor(task.priority)}>
          {task.priority} Priority
        </Badge>
        {task.tags?.map(tag => (
          <Badge
            key={tag.id}
            variant="outline"
            className="border-transparent"
            style={{
              backgroundColor: tag.color ? `${tag.color}20` : undefined,
              color: tag.color || undefined,
            }}
          >
            {tag.name}
          </Badge>
        ))}
      </div>

      <div className="grid gap-6 md:grid-cols-3">
        {/* Main Content */}
        <div className="md:col-span-2 space-y-6">
          {/* Description */}
          <Card>
            <CardHeader>
              <CardTitle>Description</CardTitle>
            </CardHeader>
            <CardContent>
              <div className="text-sm leading-relaxed whitespace-pre-wrap">
                {task.description || "No description provided"}
              </div>
            </CardContent>
          </Card>

          {/* AI Assistant Section */}
          <Card className="bg-indigo-50 dark:bg-indigo-950/30 border-indigo-100 dark:border-indigo-900">
            <CardHeader>
              <div className="flex items-center justify-between">
                <div className="flex items-center gap-2">
                  <Sparkles className="h-4 w-4 text-indigo-600 dark:text-indigo-400" />
                  <CardTitle className="text-indigo-900 dark:text-indigo-200">AI Assistant</CardTitle>
                </div>
                
                {!isAiLoading ? (
                  <Button variant="secondary" size="sm" onClick={handleAiBreakdown}>
                    Break down task
                  </Button>
                ) : (
                  <Button variant="secondary" size="sm" disabled>
                    <Loader2 className="mr-2 h-4 w-4 animate-spin" />
                    Thinking...
                  </Button>
                )}
              </div>
            </CardHeader>
            {isAiLoading && (
              <CardContent>
                <div className="space-y-2 animate-pulse">
                  <div className="h-4 bg-indigo-200/50 rounded w-3/4"></div>
                  <div className="h-4 bg-indigo-200/50 rounded w-1/2"></div>
                </div>
              </CardContent>
            )}
          </Card>

          {/* Subtasks */}
          <Card>
            <CardHeader>
              <CardTitle>Subtasks ({completedSubtasks}/{totalSubtasks})</CardTitle>
            </CardHeader>
            <CardContent>
              {totalSubtasks === 0 ? (
                <div className="text-center py-6 text-gray-400 text-sm border-2 border-dashed border-gray-200 rounded-lg">
                  No subtasks yet. Use AI to generate them!
                </div>
              ) : (
                <div className="space-y-2">
                  {task.subtasks?.map(subtask => (
                    <div
                      key={subtask.id}
                      className="flex items-center gap-3 p-3 bg-gray-50 dark:bg-gray-800 border border-gray-200 dark:border-gray-700 rounded-md hover:bg-gray-100 dark:hover:bg-gray-700 transition-colors cursor-pointer"
                      onClick={() => toggleSubtask(subtask.id)}
                    >
                      {subtask.status === "DONE" ? (
                        <CheckSquare className="h-4 w-4 text-green-600" />
                      ) : (
                        <Square className="h-4 w-4 text-gray-400" />
                      )}
                      <span className={cn(
                        "text-sm",
                        subtask.status === "DONE" && "line-through text-gray-400"
                      )}>
                        {subtask.title}
                      </span>
                    </div>
                  ))}
                </div>
              )}
            </CardContent>
          </Card>
        </div>

        {/* Sidebar */}
        <div className="space-y-6">
          {/* Task Details */}
          <Card>
            <CardHeader>
              <CardTitle>Task Details</CardTitle>
            </CardHeader>
            <CardContent className="space-y-4">
              <div className="flex items-center gap-2 text-sm">
                <Calendar className="h-4 w-4 text-gray-400" />
                <span className="text-gray-600">Due:</span>
                <span className="font-medium">
                  {task.dueDate ? new Date(task.dueDate).toLocaleDateString() : "No due date"}
                </span>
              </div>
              
              <div className="flex items-center gap-2 text-sm">
                <User className="h-4 w-4 text-gray-400" />
                <span className="text-gray-600">Assigned to:</span>
                <span className="font-medium">
                  {task.assignee 
                    ? `${task.assignee.firstName || ""} ${task.assignee.lastName || ""}`.trim() || task.assignee.email
                    : "Unassigned"
                  }
                </span>
              </div>

              <div className="text-sm">
                <span className="text-gray-600">Order:</span>
                <span className="font-medium ml-2">{task.order}</span>
              </div>

              <div className="text-sm">
                <span className="text-gray-600">Created:</span>
                <span className="font-medium ml-2">
                  {new Date(task.createdAt).toLocaleDateString()}
                </span>
              </div>

              <div className="text-sm">
                <span className="text-gray-600">Updated:</span>
                <span className="font-medium ml-2">
                  {new Date(task.updatedAt).toLocaleDateString()}
                </span>
              </div>
            </CardContent>
          </Card>

          {/* Parent Task */}
          {task.parent && (
            <Card>
              <CardHeader>
                <CardTitle>Parent Task</CardTitle>
              </CardHeader>
              <CardContent>
                <Button
                  variant="link"
                  className="p-0 h-auto font-normal"
                  onClick={() => router.push(`/tasks/${task.parent?.id}`)}
                >
                  {task.parent.title}
                </Button>
              </CardContent>
            </Card>
          )}
        </div>
      </div>
    </div>
  );
}