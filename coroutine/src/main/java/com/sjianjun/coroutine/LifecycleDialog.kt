package com.sjianjun.coroutine

import android.app.Dialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import android.content.Context
import android.view.ViewGroup

/*
 * Created by shen jian jun on 2020-07-30
 */
/*
 * Created by shen jian jun on 2020-07-30
 */
open class LifecycleDialog(context: Context) : Dialog(context) {
    private var lifecycleRegistry: LifecycleRegistry? = null

    var lifecycleOwner: LifecycleOwner? = null
        private set
        get() {
            if (field == null) {
                field = LifecycleOwner {
                    if (lifecycleRegistry == null) {
                        lifecycleRegistry = LifecycleRegistry(field!!)
                    }
                    lifecycleRegistry!!
                }
            }
            return field
        }

    init {
        //创建对象
        lifecycleOwner?.lifecycle
    }

    override fun onStart() {
        super.onStart()
        lifecycleRegistry?.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        lifecycleRegistry?.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        lifecycleRegistry?.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    }

    override fun onStop() {
        super.onStop()
        lifecycleRegistry?.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

    override fun show() {
        super.show()
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}