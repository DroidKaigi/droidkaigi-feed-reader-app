package io.github.droidkaigi.feeder

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

data class Announcement(
    val id: String,
    val title: String,
    val content: String,
    val type: Type,
    val publishedAt: Instant,
    val language: Language,
) {
    enum class Type {
        ALERT,
        NOTIFICATION,
        FEEDBACK,
        ;
    }

    enum class Language {
        JAPANESE,
        ENGLISH,
        ;
    }
}

data class NotificationContents(
    val announcements: List<Announcement> = listOf(),
)

fun fakeNotificationContents(): NotificationContents = NotificationContents(
    announcements = listOf(
        Announcement(
            id = "1",
            title = "title 1",
            content = "content 1",
            type = Announcement.Type.ALERT,
            publishedAt = LocalDateTime
                .parse("2022-03-30T00:00:00")
                .toInstant(TimeZone.of("UTC+9")),
            language = Announcement.Language.JAPANESE,
        ),
        Announcement(
            id = "2",
            title = "title 2 the title of this content is very very very long.",
            content = "content 2",
            type = Announcement.Type.NOTIFICATION,
            publishedAt = LocalDateTime
                .parse("2022-03-20T00:00:00")
                .toInstant(TimeZone.of("UTC+9")),
            language = Announcement.Language.ENGLISH,
        ),
        Announcement(
            id = "3",
            title = "title 3",
            content = "content 3 is long content. it is too long. it is very very long.",
            type = Announcement.Type.FEEDBACK,
            publishedAt = LocalDateTime
                .parse("2022-03-10T00:00:00")
                .toInstant(TimeZone.of("UTC+9")),
            language = Announcement.Language.JAPANESE,
        ),
        Announcement(
            id = "4",
            title = "title 4",
            content = "content 4",
            type = Announcement.Type.FEEDBACK,
            publishedAt = LocalDateTime
                .parse("2022-02-20T00:00:00")
                .toInstant(TimeZone.of("UTC+9")),
            language = Announcement.Language.JAPANESE,
        ),
        Announcement(
            id = "5",
            title = "title 5",
            content = "content 5",
            type = Announcement.Type.FEEDBACK,
            publishedAt = LocalDateTime
                .parse("2022-02-10T00:00:00")
                .toInstant(TimeZone.of("UTC+9")),
            language = Announcement.Language.JAPANESE,
        ),
        Announcement(
            id = "6",
            title = "title 6",
            content = "content 6",
            type = Announcement.Type.FEEDBACK,
            publishedAt = LocalDateTime
                .parse("2022-01-30T00:00:00")
                .toInstant(TimeZone.of("UTC+9")),
            language = Announcement.Language.JAPANESE,
        ),
    )
)
