package io.github.droidkaigi.feeder.notification

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.droidkaigi.feeder.NotificationItem
import io.github.droidkaigi.feeder.NotificationItemId

@Composable
fun NotificationItem(
    notificationItem: NotificationItem,
    onDetailClick: (NotificationItemId) -> Unit,
) {
}

@Preview(showBackground = true)
@Composable
fun PreviewNotificationItem() {
    Column {
//        fakeNotificationContents().notificationItems.notificationItems.forEachIndexed { index, item ->
//            NotificationItem(NotificationItemState(item, true), {})
//        }
    }
}
