package com.denisbovsunivskyi.animetier.presentation.utils.edittext

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.denisbovsunivskyi.animetier.presentation.utils.edittext.KeyboardHelper.clearFocusOnKeyboardHidden
import com.denisbovsunivskyi.animetier.presentation.utils.edittext.listener.OnKeyActionDoneListener
import com.denisbovsunivskyi.animetier.presentation.utils.edittext.listener.OnKeyActionSearchListener
import com.denisbovsunivskyi.animetier.presentation.utils.extensions.setHideKeyboardWrapperListener
import com.denisbovsunivskyi.animetier.presentation.utils.isDonePressed
import com.denisbovsunivskyi.animetier.presentation.utils.isEnterPressed
import com.denisbovsunivskyi.animetier.presentation.utils.isNextPressed
import com.denisbovsunivskyi.animetier.presentation.utils.isSearchPressed
import com.google.android.material.textfield.TextInputEditText

class ClearFocusEditText : TextInputEditText {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet
    ) : super(context, attrs) {
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        initEditorActionListener()
        initCleanFocusWithRouting()
        this.clearFocusOnKeyboardHidden()
    }

    private var actionDoneListener: OnKeyActionDoneListener? = null
    private var actionSearchListener: OnKeyActionSearchListener? = null
    private var keyMode: KeyMode = KeyMode.ACTION_DONE
    private var targetFocusView: View? = null
    private var nextTargetView: View? = null

    private fun initEditorActionListener() {
        setOnEditorActionListener { _, keyCode, keyEvent ->
            if (isDonePressed(keyCode) ||
                isEnterPressed(keyEvent)
            ) {
                keyMode = KeyMode.ACTION_DONE
//                clearFocus()
                routeFocusTargetView()
                actionDoneListener?.onActionDone()
            }
            if (isNextPressed(keyCode)) {
                keyMode = KeyMode.ACTION_NEXT
                routeFocusNextView()
            }
            if (isSearchPressed(keyCode)) {
                keyMode = KeyMode.ACTION_SEARCH
                this.clearFocus()
                actionSearchListener?.onActionSearch()
                routeFocusTargetView()
            }
            true
        }
    }


    override fun setOnFocusChangeListener(listener: OnFocusChangeListener?) {
        super.setOnFocusChangeListener { view, focus ->
            listener?.onFocusChange(view, focus)
            if (!focus) {
                handleActionPressed()
            }
        }
    }

    private fun initCleanFocusWithRouting() {
        setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                handleActionPressed()
            }
        }
    }

    private fun handleActionPressed() {
        when (keyMode) {
            KeyMode.ACTION_DONE -> routeFocusTargetView()
            KeyMode.ACTION_SEARCH -> routeFocusTargetView()
            KeyMode.ACTION_NEXT -> {}
        }
    }

    private fun routeFocusTargetView() {
        targetFocusView?.requestFocus()
    }

    private fun routeFocusNextView() {
        nextTargetView?.requestFocus()
    }


    fun setOnActionDoneListener(listener: OnKeyActionDoneListener?) {
        this.actionDoneListener = listener
    }

    fun setOnActionSearchListener(listener: OnKeyActionSearchListener?) {
        this.actionSearchListener = listener
    }

    fun setTargetFocusView(targetView: View) {
        this.targetFocusView = targetView
    }

    fun setNextTargetView(nextTargetView: View) {
        this.nextTargetView = nextTargetView
    }

    fun setTargetForCleanFocus(targetView: View) {
        this.targetFocusView = targetView
        this.targetFocusView?.setHideKeyboardWrapperListener()
    }
}
