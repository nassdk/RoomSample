package com.test.roomsample.library.coreui.tab

import android.os.Bundle
import com.test.roomsample.R
import com.test.roomsample.library.coreui.base.BaseFragment
import com.test.roomsample.library.coreui.navigation.ContainerIdProvider
import me.aartikov.alligator.Navigator
import me.aartikov.alligator.ScreenResolver
import javax.inject.Inject

class TabFragment : BaseFragment(R.layout.layout_screen_container), ContainerIdProvider {

    @Inject lateinit var navigator: Navigator
    @Inject lateinit var screenResolver: ScreenResolver

    override val containerId: Int
        get() = R.id.screen_container

    override fun onCreate(savedInstanceState: Bundle?) {
        TabComponent
            .create(requireActivity())
            .inject(this)
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val tabScreen = screenResolver.getScreen<TabScreen>(this)
            navigator.reset(tabScreen.innerScreen)
        }
    }
}

