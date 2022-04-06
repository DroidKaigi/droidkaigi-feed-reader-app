package io.github.droidkaigi.feeder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.droidkaigi.feeder.AppError
import io.github.droidkaigi.feeder.notification.NotificationViewModel
import io.github.droidkaigi.feeder.repository.NotificationRepository
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// TODO: Temporary implementation, modify later.
@HiltViewModel
class RealNotificationViewModel @Inject constructor(
    private val notificationRepository: NotificationRepository,
) : ViewModel(), NotificationViewModel {

    private val effectChannel = Channel<NotificationViewModel.Effect>(Channel.UNLIMITED)
    override val effect: Flow<NotificationViewModel.Effect> = effectChannel.receiveAsFlow()

    init {
        viewModelScope.launch { refreshRepository() }
    }

    override val state: StateFlow<NotificationViewModel.State> =
        notificationRepository.notificationContents().map { notificationContents ->
            NotificationViewModel.State(
                notificationContents = notificationContents,
            )
        }.stateIn(viewModelScope, SharingStarted.Eagerly, NotificationViewModel.State())

    override fun event(event: NotificationViewModel.Event) {
        // TODO: Implement later
    }

    private suspend fun refreshRepository() {
        try {
            notificationRepository.refresh()
        } catch (e: AppError) {
            effectChannel.send(NotificationViewModel.Effect.ErrorMessage(e))
        }
    }
}
