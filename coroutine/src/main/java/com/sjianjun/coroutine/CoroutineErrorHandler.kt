/*
 * Created by shen jian jun on 2020-06-18
 */
package com.sjianjun.coroutine

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler

var coroutineErrorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    Log.e("coroutine error","coroutineContext:$coroutineContext throwable:$throwable", throwable)
}