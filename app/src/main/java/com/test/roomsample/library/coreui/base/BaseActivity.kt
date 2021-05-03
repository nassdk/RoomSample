package com.test.roomsample.library.coreui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(@LayoutRes layoutRes: Int) : AppCompatActivity(layoutRes) {

    override fun onCreate(savedInstanceState: Bundle?) {
        invokeDi()
        super.onCreate(savedInstanceState)
        prepareUi()
    }

    open fun invokeDi() = Unit
    open fun prepareUi() = Unit
}