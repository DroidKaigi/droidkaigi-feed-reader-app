package io.github.droidkaigi.feeder.data

import io.github.droidkaigi.feeder.NotificationItem
import io.github.droidkaigi.feeder.data.response.NotificationResponse

open class KtorNotificationApi(
    private val networkService: NetworkService,
) : NotificationApi {

    override suspend fun fetch(language: NotificationApi.Language): List<NotificationItem> {
        return networkService.get<NotificationResponse>(
            "https://${BuildKonfig.API_END_PONT}/announcements/${language.id}",
            needAuth = true
        ).toNotificationList()
    }
}

fun NotificationResponse.toNotificationList(): List<NotificationItem> {
    return announcements.map { announcement ->
        NotificationItem(
            id = announcement.id,
            title = announcement.title,
            content = announcement.content,
            type = NotificationItem.Type.from(announcement.type),
            publishedAt = announcement.publishedAt,
            deletedAt = announcement.deletedAt,
            language = NotificationItem.Language.from(announcement.language),
        )
    }
}
