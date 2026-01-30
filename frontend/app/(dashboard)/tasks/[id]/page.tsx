"use server";

import { api } from "@/lib/server";
import PageContent from "./_components/page-content";

export default async function TaskDetailPage({
  params,
}: {
  params: Promise<{ [key: string]: string }>;
}) {
  const { id } = await params;
  const task = await api.tasks.getById(id);

  return <PageContent initialTask={task} />;
}
