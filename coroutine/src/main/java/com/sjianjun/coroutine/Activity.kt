package com.sjianjun.coroutine

import androidx.activity.ComponentActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/*
 * Created by shen jian jun on 2020-07-14
 */


fun ComponentActivity.launchIo(
    singleCoroutineKey: String = "",
    context: CoroutineContext = Dispatchers.IO,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return lifecycle.launch(singleCoroutineKey, context, start, block)
}

fun ComponentActivity.launch(
    singleCoroutineKey: String = "",
    context: CoroutineContext = Dispatchers.Main,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return lifecycle.launch(singleCoroutineKey, context, start, block)
}
