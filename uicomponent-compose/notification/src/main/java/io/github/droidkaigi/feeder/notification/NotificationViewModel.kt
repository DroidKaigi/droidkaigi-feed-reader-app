package io.github.droidkaigi.feeder.notification

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import io.github.droidkaigi.feeder.AppError
import io.github.droidkaigi.feeder.NotificationContents
import io.github.droidkaigi.feeder.core.UnidirectionalViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface NotificationViewModel :
    UnidirectionalViewModel<
        NotificationViewModel.Event,
        NotificationViewModel.Effect,
        NotificationViewModel.State
        > {
    data class State(
        val showProgress: Boolean = false,
        val notificationContents: NotificationContents = TODO(), /* fakeNotificationContents() */
    )

    sealed class Effect {
        data class ErrorMessage(val appError: AppError) : Effect()
    }

    sealed class Event

    override val state: StateFlow<State>
    override val effect: Flow<Effect>
    override fun event(event: Event)
}

private val LocalNotificationViewModel =
    compositionLocalOf<@Composable () -> NotificationViewModel> {
        {
            error("not LocalNotificationViewModel provided")
        }
    }

fun provideNotificationViewModelFactory(viewModel: @Composable () -> NotificationViewModel) =
    LocalNotificationViewModel provides viewModel

@Composable
fun notificationViewModel() = LocalNotificationViewModel.current()
