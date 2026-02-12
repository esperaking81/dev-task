package dev.espera.devtask.data.datasources.converters

import java.time.LocalDateTime
import androidx.room.TypeConverter
import java.time.format.DateTimeFormatter

class DateTimeConverters {
    companion object {
        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    }

    @TypeConverter
    fun fromIsoString(isoString: String): LocalDateTime {
        return LocalDateTime.parse(isoString, formatter)
    }

    @TypeConverter
    fun toIsoString(dateTime: LocalDateTime): String {
        return dateTime.format(formatter)
    }
}