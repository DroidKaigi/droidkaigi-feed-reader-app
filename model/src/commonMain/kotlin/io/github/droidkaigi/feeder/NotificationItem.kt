package io.github.droidkaigi.feeder

import kotlinx.datetime.Instant

data class NotificationItem(
    val id: String,
    val title: String,
    val content: String,
    val type: Type,
    val publishedAt: Instant,
    val deletedAt: Instant?,
    val language: Language,
) {

    enum class Type(val value: String) {
        ALERT("ALERT"),
        NOTIFICATION("NOTIFICATION"),
        FEEDBACK("FEEDBACK"),
        ;

        companion object {
            fun from(typeStr: String): Type {
                return values().find { type ->
                    type.value == typeStr
                } ?: NOTIFICATION // Return default value.
            }
        }
    }

    enum class Language(val value: String) {
        JAPANESE("japanese"),
        ENGLISH("english"),
        ;

        companion object {
            fun from(languageStr: String): Language {
                return values().find { language ->
                    language.value == languageStr
                } ?: JAPANESE // Return default value.
            }
        }
    }
}
