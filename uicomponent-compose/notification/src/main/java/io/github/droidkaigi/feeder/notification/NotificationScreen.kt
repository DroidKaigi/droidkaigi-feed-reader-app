package io.github.droidkaigi.feeder.notification

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi
import io.github.droidkaigi.feeder.NotificationContents
import io.github.droidkaigi.feeder.NotificationItemId
import io.github.droidkaigi.feeder.core.R
import io.github.droidkaigi.feeder.core.theme.AppThemeWithBackground
import io.github.droidkaigi.feeder.core.use


sealed class NotificationTab(val name: String, val routePath: String) {
    object Alert : NotificationTab("Alert", "alert")
    object Notification : NotificationTab("Notification", "notification")
    object Feedback : NotificationTab("Feedback", "feedback")

    companion object {
        fun values() = listOf(Alert, Notification, Feedback)
        fun ofRoutePath(routePath: String) =
            values().find { it.routePath == routePath } ?: Notification
    }
}

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
    val (state, effectFlow, dispatch) = use(
        // FIXME: Use notificationViewModel()
        // notificationViewModel()
        fakeNotificationViewModel()
    )

    NotificationScreen(
        state = NotificationScreenState(notificationContents = state.notificationContents),
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
    Scaffold(
        topBar = {
            AppBar(onNavigationIconClick)
        }
    ) {
        NotificationList(notificationContents = state.notificationContents)
    }
}

@Composable
private fun NotificationList(
    notificationContents: NotificationContents,
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxHeight()
    ) {
        LazyColumn(
            contentPadding = rememberInsetsPaddingValues(
                insets = LocalWindowInsets.current.systemBars,
                applyStart = false,
                applyTop = false,
                applyEnd = false
            )
        ) {
            items(notificationContents.notificationItems) { item ->
                // FIXME: Apply design.
                Column {
                    Text(text = item.title)
                    Text(text = item.content)
                    Text(text = item.publishedAt.toString())
                    Divider()
                }
            }
        }
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun AppBar(
    onNavigationIconClick: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier
            .background(MaterialTheme.colors.primarySurface)
            .statusBarsPadding(),
        title = { Image(painterResource(R.drawable.toolbar_droidkaigi_logo), "DroidKaigi") },
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(painterResource(R.drawable.ic_baseline_menu_24), "menu")
            }
        }
    )
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
