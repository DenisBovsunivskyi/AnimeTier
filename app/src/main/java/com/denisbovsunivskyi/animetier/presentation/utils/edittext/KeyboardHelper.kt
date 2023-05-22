package com.denisbovsunivskyi.animetier.presentation.utils.edittext

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver

object KeyboardHelper {

    fun View?.clearFocusOnKeyboardHidden() {
        var wasOpened = false
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val isOpen = isKeyboardVisible(this@clearFocusOnKeyboardHidden?.context?.getActivity())
            if (isOpen == wasOpened) {
                // The keyboard state has not changed yet!
                return@OnGlobalLayoutListener
            }
            wasOpened = isOpen
            if (this@clearFocusOnKeyboardHidden?.isFocused == true && !isOpen) {
                this@clearFocusOnKeyboardHidden.clearFocus()
            }
        }
        this?.viewTreeObserver?.addOnGlobalLayoutListener(listener)
    }

    private fun isKeyboardVisible(activity: Activity?): Boolean {
        activity ?: return false

        val outRect = Rect()

        val activityRoot = activity.getActivityRoot()

        activityRoot.getWindowVisibleDisplayFrame(outRect)

        val location = IntArray(2)
        activity.getContentRoot().getLocationOnScreen(location)

        val screenHeight = activityRoot.rootView.height
        val heightDiff = screenHeight - outRect.height() - location[1]

        return heightDiff > screenHeight * 0.15
    }

    private fun Context.getActivity(): Activity? {
        return when (this) {
            is Activity -> this
            is ContextWrapper -> this.baseContext.getActivity()
            else -> null
        }
    }

    private fun Activity.getActivityRoot(): View {
        return this.getContentRoot().rootView
    }

    private fun Activity.getContentRoot(): ViewGroup {
        return this.findViewById(android.R.id.content)
    }

}