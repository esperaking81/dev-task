package dev.espera.devtask.data.datasources.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import dev.espera.devtask.domain.models.User

@Entity(
    tableName = "users",
    indices = [Index("email", unique = true)]
)
data class UserEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "created_at") val createdAt: String,
    @ColumnInfo(name = "updated_at") val updatedAt: String
)

fun UserEntity.toDomain(): User = User(
    id = id,
    name = name,
    email = email,
)