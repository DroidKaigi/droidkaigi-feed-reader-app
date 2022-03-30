package io.github.droidkaigi.feeder.notification

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import io.github.droidkaigi.feeder.NotificationContents
import io.github.droidkaigi.feeder.NotificationItemId
import io.github.droidkaigi.feeder.core.theme.AppThemeWithBackground
import io.github.droidkaigi.feeder.core.use

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Stable
data class NotificationScreenState(
    val notificationContents: NotificationContents,
)

/**
 * stateful
 */
@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun NotificationScreen(
    onNavigationIconClick: () -> Unit,
    onDetailClick: (NotificationItemId) -> Unit,
) {
    val (state, effectFlow, dispatch) = use(notificationViewModel())

    NotificationScreen(
        state = NotificationScreenState(
            notificationContents = state.notificationContents,
        ),
        onNavigationIconClick = onNavigationIconClick,
        onDetailClick = onDetailClick,
    )
}

/**
 * Stateless
 */
@Composable
private fun NotificationScreen(
    state: NotificationScreenState,
    onNavigationIconClick: () -> Unit,
    onDetailClick: (NotificationItemId) -> Unit,
) {
}

@Preview(showBackground = true)
@Composable
fun PreviewNotificationScreen() {
    AppThemeWithBackground {
        CompositionLocalProvider(
            provideNotificationViewModelFactory { fakeNotificationViewModel() },
        ) {
            NotificationScreen(
                onNavigationIconClick = {},
                onDetailClick = {},
            )
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewDarkNotificationScreen() {
    AppThemeWithBackground {
        CompositionLocalProvider(
            provideNotificationViewModelFactory { fakeNotificationViewModel() },
        ) {
            NotificationScreen(
                onNavigationIconClick = {},
                onDetailClick = {},
            )
        }
    }
}
