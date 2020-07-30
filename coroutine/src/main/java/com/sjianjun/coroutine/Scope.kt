package com.sjianjun.coroutine

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.coroutines.CoroutineContext

/*
 * Created by shen jian jun on 2020-07-30
 */
private val concurrentHashMap = ConcurrentHashMap<Lifecycle, Lazy<LifecycleCoroutineScope>>()

val Lifecycle.coroutineScope: LifecycleCoroutineScope
    get() {

        val scopeLazy = concurrentHashMap.getOrPut(this) {
            var lazy: Lazy<LifecycleCoroutineScope>? = null
            lazy = lazy {
                LifecycleCoroutineScope(this@coroutineScope, lazy!!, SupervisorJob() + Dispatchers.Main)
                    .register()
            }
            lazy
        }

        return scopeLazy.value

    }
val Lifecycle.newCoroutineScope: LifecycleCoroutineScope
    get() = LifecycleCoroutineScope(this, null, SupervisorJob() + Dispatchers.Main).register()

class LifecycleCoroutineScope(
    val lifecycle: Lifecycle,
    val lazyScope: Lazy<LifecycleCoroutineScope>?,
    override val coroutineContext: CoroutineContext
) : CoroutineScope, LifecycleObserver {
    init {
        if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
            coroutineContext.cancel()
            if (lazyScope != null) {
                concurrentHashMap.remove(lifecycle, lazyScope)
            }
        }
    }

    fun register(): LifecycleCoroutineScope {
        coroutineContext[Job]?.invokeOnCompletion {
            lifecycle.removeObserver(this)
            concurrentHashMap.remove(lifecycle, lazyScope ?: return@invokeOnCompletion)
        }

        launch(Dispatchers.Main) {
            if (lifecycle.currentState >= Lifecycle.State.INITIALIZED) {
                lifecycle.addObserver(this@LifecycleCoroutineScope)
            } else {
                coroutineContext.cancel()
                concurrentHashMap.remove(lifecycle, lazyScope ?: return@launch)
            }
        }

        return this
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onStateChanged() {
        if (lifecycle.currentState <= Lifecycle.State.DESTROYED) {
            lifecycle.removeObserver(this)
            coroutineContext.cancel()
            concurrentHashMap.remove(lifecycle, lazyScope ?: return)
        }
    }
}