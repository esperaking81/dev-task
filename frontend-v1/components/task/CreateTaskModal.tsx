import { useState, useEffect } from "react"
import { Modal } from "@/components/ui/modal"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Badge } from "@/components/ui/badge"
import { X } from "lucide-react"
import { api } from "@/lib/api"
import { Tag } from "@/types"

interface CreateTaskModalProps {
    isOpen: boolean
    onClose: () => void
    onCreate: (taskData: any) => Promise<void>
}

export function CreateTaskModal({ isOpen, onClose, onCreate }: CreateTaskModalProps) {
    const [title, setTitle] = useState("")
    const [description, setDescription] = useState("")
    const [availableTags, setAvailableTags] = useState<Tag[]>([])
    const [selectedTagIds, setSelectedTagIds] = useState<string[]>([])
    const [isLoading, setIsLoading] = useState(false)

    useEffect(() => {
        if (isOpen) {
            const fetchTags = async () => {
                try {
                    const tags = await api.tags.getAll()
                    setAvailableTags(tags)
                } catch (error) {
                    console.error("Failed to fetch tags", error)
                }
            }
            fetchTags()
        }
    }, [isOpen])

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault()
        if (!title.trim()) return

        setIsLoading(true)
        try {
            await onCreate({
                title,
                description,
                status: "TODO",
                tags: selectedTagIds
            })
            // Reset form
            setTitle("")
            setDescription("")
            setSelectedTagIds([])
            onClose()
        } catch (error) {
            console.error("Failed to create task", error)
        } finally {
            setIsLoading(false)
        }
    }

    const toggleTag = (tagId: string) => {
        if (selectedTagIds.includes(tagId)) {
            setSelectedTagIds(prev => prev.filter(id => id !== tagId))
        } else {
            setSelectedTagIds(prev => [...prev, tagId])
        }
    }

    return (
        <Modal isOpen={isOpen} onClose={onClose} title="Create New Task">
            <form onSubmit={handleSubmit} className="space-y-6">
                <div className="space-y-4">
                    <div className="space-y-2">
                        <label className="text-sm font-medium text-neutral-700 dark:text-neutral-300">Title</label>
                        <Input
                            placeholder="Task title"
                            value={title}
                            onChange={e => setTitle(e.target.value)}
                            required
                        />
                    </div>

                    <div className="space-y-2">
                        <label className="text-sm font-medium text-neutral-700 dark:text-neutral-300">Description</label>
                        <textarea
                            className="flex min-h-[80px] w-full rounded-md border border-neutral-200 bg-white px-3 py-2 text-sm ring-offset-white placeholder:text-neutral-500 focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-neutral-950 focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50 dark:border-neutral-800 dark:bg-neutral-950 dark:ring-offset-neutral-950 dark:placeholder:text-neutral-400 dark:focus-visible:ring-neutral-300"
                            placeholder="Add a more detailed description..."
                            value={description}
                            onChange={e => setDescription(e.target.value)}
                        />
                    </div>

                    <div className="space-y-2">
                        <label className="text-sm font-medium text-neutral-700 dark:text-neutral-300">Tags</label>
                        <div className="flex flex-wrap gap-2">
                            {availableTags.length === 0 ? (
                                <p className="text-sm text-neutral-500">No tags available.</p>
                            ) : (
                                availableTags.map(tag => (
                                    <Badge
                                        key={tag.id}
                                        variant="outline"
                                        className={`cursor-pointer select-none border-transparent ${selectedTagIds.includes(tag.id) ? 'ring-2 ring-neutral-400' : ''}`}
                                        style={{
                                            backgroundColor: tag.color ? `${tag.color}20` : undefined,
                                            color: tag.color,
                                            opacity: selectedTagIds.includes(tag.id) ? 1 : 0.6
                                        }}
                                        onClick={() => toggleTag(tag.id)}
                                    >
                                        {tag.name}
                                        {selectedTagIds.includes(tag.id) && <X className="ml-1 h-3 w-3" />}
                                    </Badge>
                                ))
                            )}
                        </div>
                    </div>
                </div>

                <div className="flex justify-end gap-2">
                    <Button type="button" variant="ghost" onClick={onClose}>Cancel</Button>
                    <Button type="submit" disabled={isLoading || !title}>
                        {isLoading ? "Creating..." : "Create Task"}
                    </Button>
                </div>
            </form>
        </Modal>
    )
}
