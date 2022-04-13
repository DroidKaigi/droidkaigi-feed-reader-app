package io.github.droidkaigi.feeder.data.response

import kotlinx.datetime.Instant
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Announcement(
    val id: String,
    val title: String,
    val content: String,
    val type: String,
    @Contextual
    val publishedAt: Instant,
    @Contextual
    val deletedAt: Instant? = null,
    val language: String,
)

@Serializable
data class NotificationResponse(
    val status: String,
    val announcements: List<Announcement>,
)
