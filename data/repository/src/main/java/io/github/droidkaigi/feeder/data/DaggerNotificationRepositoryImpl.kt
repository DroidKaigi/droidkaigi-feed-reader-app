package io.github.droidkaigi.feeder.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DaggerNotificationRepositoryImpl @Inject constructor(
    notificationApi: NotificationApi,
    userDataStore: UserDataStore,
) : NotificationRepositoryImpl(
    notificationApi,
    userDataStore,
)
