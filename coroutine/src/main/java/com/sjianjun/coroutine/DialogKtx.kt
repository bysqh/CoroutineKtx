package com.sjianjun.coroutine

import android.app.Activity
import android.app.Dialog
import android.support.annotation.IdRes
import android.view.View
import android.widget.TextView

fun dialog(
    activity: Activity,
    show: Boolean = true,
    apply: LifecycleDialog.() -> Unit = {}
): LifecycleDialog {
    val dialog = LifecycleDialog(activity).apply(apply)
    if (show) {
        dialog.show()
    }
    return dialog
}

fun <T : View> Dialog.view(@IdRes id: Int): T? {
    val view = window?.decorView
    return view?.findViewById(id)
}

fun Dialog.setText(@IdRes id: Int, text: CharSequence?): Dialog {
    view<TextView>(id)?.text = text ?: ""
    return this
}


fun Dialog.onClick(l: (view: View, dialog: Dialog) -> Unit, @IdRes vararg ids: Int): Dialog {
    return onClick(l, true, *ids)

}

fun Dialog.onClick(
    l: (view: View, dialog: Dialog) -> Unit,
    dismiss: Boolean,
    @IdRes vararg ids: Int
): Dialog {
    ids.forEach {
        view<View>(it)?.setOnClickListener { view ->
            if (dismiss) {
                dismiss()
            }
            l(view, this)
        }
    }
    return this
}

fun Dialog.bindDismiss(@IdRes vararg ids: Int): Dialog {
    return onClick({ _, _ -> }, true, *ids)
}