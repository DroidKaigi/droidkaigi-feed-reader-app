package io.github.droidkaigi.feeder.data

import io.github.droidkaigi.feeder.Lang
import io.github.droidkaigi.feeder.NotificationContents
import io.github.droidkaigi.feeder.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull

// TODO: Temporary implementation, modify later
open class NotificationRepositoryImpl(
    private val notificationApi: NotificationApi,
    private val dataStore: UserDataStore,
) : NotificationRepository {

    // TODO: Temporary implementation. Replace with persistent store if needed
    private val notificationContents = MutableStateFlow(NotificationContents())

    override fun notificationContents(): Flow<NotificationContents> {
        return notificationContents
    }

    override suspend fun refresh() {
        notificationContents.value =
            NotificationContents(notificationItems = notificationApi.fetch(getAppliedLanguage()))
    }

    private suspend fun getAppliedLanguage(): NotificationApi.Language {
        return when (dataStore.language().firstOrNull()) {
            Lang.JA -> NotificationApi.Language.JAPANESE
            else -> NotificationApi.Language.ENGLISH
        }
    }
}
