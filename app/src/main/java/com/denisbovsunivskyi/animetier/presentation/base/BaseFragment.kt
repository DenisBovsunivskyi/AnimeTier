package com.denisbovsunivskyi.animetier.presentation.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        initWithBundle(savedInstanceState)
        initViews()
        initViewModels()
        initListeners()
    }

    abstract fun init()

    protected open fun initViews() {}

    protected open fun initWithBundle(savedInstanceState: Bundle?) {}

    protected open fun initViewModels() {}
    protected open fun initListeners() {}

}
