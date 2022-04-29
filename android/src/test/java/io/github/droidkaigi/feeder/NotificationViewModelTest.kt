package io.github.droidkaigi.feeder

import io.github.droidkaigi.feeder.data.NotificationRepositoryImpl
import io.github.droidkaigi.feeder.data.fakeNotificationApi
import io.github.droidkaigi.feeder.data.fakeUserDataStore
import io.github.droidkaigi.feeder.notification.NotificationViewModel
import io.github.droidkaigi.feeder.notification.fakeNotificationViewModel
import io.github.droidkaigi.feeder.viewmodel.RealNotificationViewModel
import io.kotest.matchers.comparables.shouldBeGreaterThan
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@InternalCoroutinesApi
@RunWith(Parameterized::class)
class NotificationViewModelTest(
    @Suppress("unused")
    val name: String,
    private val notificationViewModelFactory: NotificationViewModelFactory,
) {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Test
    fun contents() = coroutineTestRule.scope.runTest {
        val notificationViewModel = notificationViewModelFactory.create()
        advanceUntilIdle()

        val notificationContents = notificationViewModel.state.value.notificationContents
        notificationContents.notificationItems.size shouldBeGreaterThan 1
    }

    class NotificationViewModelFactory(
        private val viewModelFactory: (errorFetchData: Boolean) -> NotificationViewModel,
    ) {
        fun create(errorFetchData: Boolean = false) = viewModelFactory(errorFetchData)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        fun data() = listOf(
            arrayOf(
                "Real ViewModel and Repository",
                NotificationViewModelFactory { errorFetchData ->
                    RealNotificationViewModel(
                        notificationRepository = NotificationRepositoryImpl(
                            notificationApi = fakeNotificationApi(
                                if (errorFetchData) {
                                    AppError.ApiException.ServerException(null)
                                } else {
                                    null
                                }
                            ),
                            dataStore = fakeUserDataStore()
                        )
                    )
                }
            ),
            arrayOf(
                "FakeViewModel",
                NotificationViewModelFactory { errorFetchData ->
                    fakeNotificationViewModel(errorFetchData)
                }
            )
        )
    }
}
