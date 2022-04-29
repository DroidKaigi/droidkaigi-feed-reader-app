package io.github.droidkaigi.feeder.data

import io.github.droidkaigi.feeder.data.response.InstantSerializer
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.json.JsonPlugin
import io.ktor.client.plugins.json.serializer.KotlinxSerializer
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual

object ApiHttpClient {
    internal fun <T> create(
        engineFactory: HttpClientEngineFactory<T>,
        userDataStore: UserDataStore,
        block: T.() -> Unit = {},
    ): HttpClient where T : HttpClientEngineConfig {
        return HttpClient(engineFactory) {
            engine(block)
            // FIXME: Migrate to ContentNegotiation
            install(JsonPlugin) {
                serializer = KotlinxSerializer(
                    Json {
                        serializersModule = SerializersModule {
                            contextual(InstantSerializer)
                        }
                        ignoreUnknownKeys = true
                        coerceInputValues = true
                        useAlternativeNames = false
                    }
                )
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        io.github.droidkaigi.feeder.Logger.d(message)
                    }
                }
                level = LogLevel.ALL
            }
            defaultRequest {
                headers {
                    userDataStore.idToken.value?.let {
                        set("Authorization", "Bearer $it")
                    }
                }
            }
        }
    }
}
