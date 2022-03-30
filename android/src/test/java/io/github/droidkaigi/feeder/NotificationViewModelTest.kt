package io.github.droidkaigi.feeder

import io.github.droidkaigi.feeder.notification.NotificationViewModel
import io.github.droidkaigi.feeder.notification.fakeNotificationViewModel
import io.kotest.matchers.comparables.shouldBeGreaterThan
import kotlinx.coroutines.InternalCoroutinesApi
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
    private val notificationViewModelFactory: NotificationViewModelFactory
) {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Test
    fun contents() = coroutineTestRule.scope.runTest {
        val notificationViewModel = notificationViewModelFactory.create()
        val notificationContents = notificationViewModel.state.value.notificationContents
        notificationContents.announcements.size shouldBeGreaterThan 1
    }

    class NotificationViewModelFactory(
        private val viewModelFactory: (errorFetchData: Boolean) -> NotificationViewModel
    ) {
        fun create(errorFetchData: Boolean = false) = viewModelFactory(errorFetchData)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        fun data() = listOf(
//            arrayOf(
//                "Real ViewModel and Repository",
//                NotificationViewModelTest.NotificationViewModelFactory { errorFetchData: Boolean ->
//                    // TODO: Implement Real ViewModel
//                }
//            ),
            arrayOf(
                "FakeViewModel",
                NotificationViewModelTest.NotificationViewModelFactory { errorFetchData ->
                    fakeNotificationViewModel(errorFetchData)
                }
            )
        )
    }
}
