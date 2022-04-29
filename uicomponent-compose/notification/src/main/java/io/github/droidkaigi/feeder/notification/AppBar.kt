package io.github.droidkaigi.feeder.notification

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import io.github.droidkaigi.feeder.Theme
import io.github.droidkaigi.feeder.core.R as CoreR
import io.github.droidkaigi.feeder.core.theme.ConferenceAppFeederTheme

@OptIn(ExperimentalPagerApi::class)
@Stable
data class AppBarState(
    val pagerState: PagerState,
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AppBar(
    appBarState: AppBarState,
    onNavigationIconClick: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier.statusBarsPadding(),
        title = {
        },
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(painterResource(CoreR.drawable.ic_baseline_menu_24), "menu")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewAppBar() {
    ConferenceAppFeederTheme {
        AppBar(
            appBarState = AppBarState(
                pagerState = PagerState(3),
            ),
            onNavigationIconClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDarkAppBar() {
    ConferenceAppFeederTheme(theme = Theme.DARK) {
        AppBar(
            appBarState = AppBarState(
                pagerState = PagerState(3),
            ),
            onNavigationIconClick = {},
        )
    }
}
