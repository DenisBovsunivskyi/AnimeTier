package com.denisbovsunivskyi.animetier.presentation.utils.edittext

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View
import com.denisbovsunivskyi.animetier.presentation.utils.isDonePressed
import com.denisbovsunivskyi.animetier.presentation.utils.isEnterPressed
import com.denisbovsunivskyi.animetier.presentation.utils.isNextPressed
import com.denisbovsunivskyi.animetier.presentation.utils.setHideKeyboardWrapperListener
import com.denisbovsunivskyi.animetier.presentation.utils.edittext.listener.OnKeyActionDoneListener
import com.denisbovsunivskyi.animetier.presentation.utils.edittext.listener.OnKeyBackPressedListener

import com.google.android.material.textfield.TextInputEditText

class ClearFocusEditText : TextInputEditText {
    constructor(context: Context) : super(context) { init() }

    constructor(
        context: Context,
        attrs: AttributeSet
    ) : super(context, attrs) { init() }

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) { init() }

    private fun init() {
        initEditorActionListener()
        initCleanFocusWithRouting()
    }

    private var actionDoneListener: OnKeyActionDoneListener? = null
    private var backListener: OnKeyBackPressedListener? = null
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
            true
        }
    }

    override fun onKeyPreIme(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            keyMode = KeyMode.ACTION_BACK
//            clearFocus()
            routeFocusTargetView()
            backListener?.onBackPressed()
        }

        return super.onKeyPreIme(keyCode, event)
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

    private fun handleActionPressed(){
        when (keyMode) {
            KeyMode.ACTION_DONE -> routeFocusTargetView()
            KeyMode.ACTION_BACK -> routeFocusTargetView()
            KeyMode.ACTION_NEXT -> {}
        }
    }

    private fun routeFocusTargetView() {
        targetFocusView?.requestFocus()
    }

    private fun routeFocusNextView() {
        nextTargetView?.requestFocus()
    }

    fun setOnBackPressedListener(listener: OnKeyBackPressedListener?) {
        this.backListener = listener
    }

    fun setOnActionDoneListener(listener: OnKeyActionDoneListener?) {
        this.actionDoneListener = listener
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
