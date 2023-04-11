package com.denisbovsunivskyi.animetier.core.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        initSplashScreen()
        super.onCreate(savedInstanceState)
        initViews()
        initViewModels()
        initListeners()


        init(savedInstanceState)
    }

    protected open fun initSplashScreen() {}

    abstract fun init(savedInstanceState: Bundle?)

    protected open fun initViews() {}
    protected open fun initViewModels() {}
    protected open fun initListeners() {}
}
