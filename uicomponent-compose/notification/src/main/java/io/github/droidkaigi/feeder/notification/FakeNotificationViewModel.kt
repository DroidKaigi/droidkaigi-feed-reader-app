package io.github.droidkaigi.feeder.notification

import androidx.lifecycle.ViewModel
import io.github.droidkaigi.feeder.AppError
import io.github.droidkaigi.feeder.NotificationContents
import io.github.droidkaigi.feeder.fakeNotificationContents
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

fun fakeNotificationViewModel(errorFetchData: Boolean = false): FakeNotificationViewModel {
    return FakeNotificationViewModel(errorFetchData)
}

class FakeNotificationViewModel(val errorFetchData: Boolean) : NotificationViewModel, ViewModel() {

    private val effectChannel = Channel<NotificationViewModel.Effect>(Channel.UNLIMITED)
    override val effect: Flow<NotificationViewModel.Effect> = effectChannel.receiveAsFlow()

    private val coroutineScope = CoroutineScope(
        object : CoroutineDispatcher() {
            // for preview
            override fun dispatch(context: CoroutineContext, block: Runnable) {
                block.run()
            }
        }
    )
    private val mutableNotificationContents = MutableStateFlow(
        fakeNotificationContents()
    )
    private val errorNotificationContents = flow<NotificationContents> {
        throw AppError.ApiException.ServerException(null)
    }
        .catch { error ->
            effectChannel.send(NotificationViewModel.Effect.ErrorMessage(error as AppError))
        }
        .stateIn(coroutineScope, SharingStarted.Lazily, fakeNotificationContents())

    private val notificationContents: StateFlow<NotificationContents> = if (errorFetchData) {
        errorNotificationContents
    } else {
        mutableNotificationContents
    }

    override val state: StateFlow<NotificationViewModel.State> =
        notificationContents.map { notificationContents ->
            NotificationViewModel.State(
                notificationContents = notificationContents,
            )
        }
            .stateIn(coroutineScope, SharingStarted.Eagerly, NotificationViewModel.State())

    override fun event(event: NotificationViewModel.Event) {
        coroutineScope.launch {
        }
    }
}
