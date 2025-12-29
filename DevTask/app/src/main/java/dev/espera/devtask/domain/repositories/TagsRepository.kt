package dev.espera.devtask.domain.repositories

import dev.espera.devtask.domain.models.Tag

interface TagsRepository {
    suspend fun getTags(): List<Tag>
}
