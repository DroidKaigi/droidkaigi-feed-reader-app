package io.github.droidkaigi.feeder.core.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

@Suppress("ComposableNaming")
@Composable
fun <T> Flow<T>.collectInLaunchedEffect(function: FlowCollector<T>) {
    val flow = this
    LaunchedEffect(key1 = flow) {
        flow.collect(function)
    }
}
