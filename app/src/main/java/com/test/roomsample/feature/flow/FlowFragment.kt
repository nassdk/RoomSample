package com.test.roomsample.feature.flow

import com.test.roomsample.R
import com.test.roomsample.databinding.ScreenFlowBinding
import com.test.roomsample.library.coreui.base.BaseFragment

class FlowFragment : BaseFragment(R.layout.screen_flow) {

    private var _viewBinding: ScreenFlowBinding? = null
    private val viewBinding: ScreenFlowBinding get() = _viewBinding!!

//    override fun bindView() {
//        _viewBinding = ScreenFlowBinding.bind(requireView())
//    }

    override fun prepareUi() {}

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
}