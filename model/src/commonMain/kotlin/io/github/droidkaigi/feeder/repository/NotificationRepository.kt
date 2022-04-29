package io.github.droidkaigi.feeder.repository

import io.github.droidkaigi.feeder.NotificationContents
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {

    /**
     * Notification contents data
     */
    fun notificationContents(): Flow<NotificationContents>

    /**
     * Refresh stored notification contents
     */
    suspend fun refresh()
}
