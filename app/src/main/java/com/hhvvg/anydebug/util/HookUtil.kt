package com.hhvvg.anydebug.util

import android.view.View
import de.robv.android.xposed.XposedHelpers

fun hookViewOnClickListener(view: View, listenerGeneratorCallback: (origin: View.OnClickListener?) -> View.OnClickListener?) {
    // Make it clickable first
    if (!view.isClickable) {
        view.isClickable = true
    }
    val info = XposedHelpers.callMethod(view, "getListenerInfo")
    val originListener = XposedHelpers.getObjectField(info, "mOnClickListener") as View.OnClickListener?
    val newListener = listenerGeneratorCallback.invoke(originListener)
    XposedHelpers.setObjectField(info, "mOnClickListener", newListener)
}