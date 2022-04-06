package io.github.droidkaigi.feeder

import kotlinx.datetime.Instant

data class NotificationItem (
    val id: String,
    val title: String,
    val content: String,
    val type: String,
    val publishedAt: Instant,
    val deletedAt: String,
    val language: String,
)
