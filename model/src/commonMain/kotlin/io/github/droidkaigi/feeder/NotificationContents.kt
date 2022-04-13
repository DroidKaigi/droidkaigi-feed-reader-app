package io.github.droidkaigi.feeder

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

data class NotificationContents(
    val notificationItems: List<NotificationItem> = listOf(),
)

fun fakeNotificationContents(): NotificationContents = NotificationContents(
    notificationItems = listOf(
        NotificationItem(
            id = "1",
            title = "title 1",
            content = "content 1",
            type = NotificationItem.Type.ALERT,
            publishedAt = LocalDateTime
                .parse("2022-03-30T00:00:00")
                .toInstant(TimeZone.of("UTC+9")),
            deletedAt = null,
            language = NotificationItem.Language.JAPANESE,
        ),
        NotificationItem(
            id = "2",
            title = "title 2 the title of this content is very very very long.",
            content = "content 2",
            type = NotificationItem.Type.NOTIFICATION,
            publishedAt = LocalDateTime
                .parse("2022-03-20T00:00:00")
                .toInstant(TimeZone.of("UTC+9")),
            deletedAt = null,
            language = NotificationItem.Language.ENGLISH,
        ),
        NotificationItem(
            id = "3",
            title = "title 3",
            content = "content 3 is long content. it is too long. it is very very long.",
            type = NotificationItem.Type.FEEDBACK,
            publishedAt = LocalDateTime
                .parse("2022-03-10T00:00:00")
                .toInstant(TimeZone.of("UTC+9")),
            deletedAt = null,
            language = NotificationItem.Language.JAPANESE,
        ),
        NotificationItem(
            id = "4",
            title = "title 4",
            content = "content 4",
            type = NotificationItem.Type.FEEDBACK,
            publishedAt = LocalDateTime
                .parse("2022-02-20T00:00:00")
                .toInstant(TimeZone.of("UTC+9")),
            deletedAt = null,
            language = NotificationItem.Language.JAPANESE,
        ),
        NotificationItem(
            id = "5",
            title = "title 5",
            content = "content 5",
            type = NotificationItem.Type.FEEDBACK,
            publishedAt = LocalDateTime
                .parse("2022-02-10T00:00:00")
                .toInstant(TimeZone.of("UTC+9")),
            deletedAt = null,
            language = NotificationItem.Language.JAPANESE,
        ),
        NotificationItem(
            id = "6",
            title = "title 6",
            content = "content 6",
            type = NotificationItem.Type.FEEDBACK,
            publishedAt = LocalDateTime
                .parse("2022-01-30T00:00:00")
                .toInstant(TimeZone.of("UTC+9")),
            deletedAt = LocalDateTime
                .parse("2022-02-28T00:00:00")
                .toInstant(TimeZone.of("UTC+9")),
            language = NotificationItem.Language.JAPANESE,
        ),
    )
)
