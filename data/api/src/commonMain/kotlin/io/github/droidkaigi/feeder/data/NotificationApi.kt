package io.github.droidkaigi.feeder.data

import io.github.droidkaigi.feeder.AppError
import io.github.droidkaigi.feeder.NotificationItem
import io.github.droidkaigi.feeder.data.response.InstantSerializer
import io.github.droidkaigi.feeder.data.response.NotificationResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual

interface NotificationApi {
    suspend fun fetch(language: Language): List<NotificationItem>

    enum class Language(val id: String) {
        JAPANESE("japanese"),
        ENGLISH("english"),
        ;
    }
}

fun fakeNotificationApi(error: AppError? = null): NotificationApi = object : NotificationApi {
    override suspend fun fetch(language: NotificationApi.Language): List<NotificationItem> {
        if (error != null) {
            throw error
        }
        return list
    }

    // cache for fixing test issue
    @OptIn(ExperimentalStdlibApi::class)
    val list: List<NotificationItem> = run {
        val responseText =
            """
            {
            	"status": "OK",
            	"announcements": [
            		{
            			"id": "a9435164-3094-4ed8-b05a-67162a5c40b1",
            			"title": "title",
            			"content": "content",
            			"publishedAt": "2022-02-18T10:58:40.496234Z",
            			"type": "FEEDBACK",
            			"language": "japanese"
            		},
            		{
            			"id": "22e65a3a-4674-463c-92cf-3180f0a2b2a5",
            			"title": "title",
            			"content": "content",
            			"publishedAt": "2022-02-17T10:58:40.497223Z",
            			"type": "NOTIFICATION",
            			"language": "japanese"
            		},
            		{
            			"id": "3bb471cc-f12b-4e4a-9fd2-98b1b36b9ee2",
            			"title": "title",
            			"content": "content",
            			"publishedAt": "2022-02-16T10:58:40.497314Z",
            			"deletedAt": "2022-02-17T10:58:40.497317Z",
            			"type": "ALERT",
            			"language": "japanese"
            		}
            	]
            }
            """

        return@run Json {
            serializersModule = SerializersModule { contextual(InstantSerializer) }
            coerceInputValues = true
        }.decodeFromString<NotificationResponse>(responseText).toNotificationList()
    }
}
