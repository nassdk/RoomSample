package com.test.roomsample.feature.live

import com.test.roomsample.R
import com.test.roomsample.databinding.ScreenLiveScoreBinding
import com.test.roomsample.library.coreui.base.BaseFragment

class LiveScoreFragment : BaseFragment(R.layout.screen_live_score) {

    private val viewBinding: ScreenLiveScoreBinding
        get() = _viewBinding!!

    private var _viewBinding: ScreenLiveScoreBinding? = null

    override fun initBindings() {
        _viewBinding = ScreenLiveScoreBinding.bind(requireView())
    }

    override fun prepareUi() {

        viewBinding.text.setOnClickListener {
            viewBinding.text.text = "clicked"
        }
    }
}