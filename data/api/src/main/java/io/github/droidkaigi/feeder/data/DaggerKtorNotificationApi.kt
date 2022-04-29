package io.github.droidkaigi.feeder.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DaggerKtorNotificationApi @Inject constructor(
    networkService: NetworkService,
) : KtorNotificationApi(networkService)
