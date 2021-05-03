package com.test.roomsample.feature.flow

import com.test.roomsample.R
import com.test.roomsample.databinding.ScreenFlowBinding
import com.test.roomsample.feature.flow.di.FlowComponent
import com.test.roomsample.library.coreui.base.BaseActivity
import com.test.roomsample.library.coreui.navigation.ContainerIdProvider
import com.test.roomsample.library.coreui.navigation.Screens
import com.test.roomsample.library.coreui.tab.TabScreen
import me.aartikov.alligator.*
import me.aartikov.alligator.navigationfactories.NavigationFactory
import me.aartikov.alligator.screenswitchers.FragmentScreenSwitcher
import javax.inject.Inject

class FlowActivity : BaseActivity(R.layout.screen_flow) {

    private var _viewBinding: ScreenFlowBinding? = null
    private val viewBinding: ScreenFlowBinding
        get() = _viewBinding!!

    @Inject lateinit var navigator: Navigator
    @Inject lateinit var navigationFactory: NavigationFactory
    @Inject lateinit var navigationContextBinder: NavigationContextBinder
    @Inject lateinit var screenResolver: ScreenResolver

    private val screenSwitcher by lazy {
        FragmentScreenSwitcher(
            navigationFactory,
            supportFragmentManager,
            R.id.flowContainer
        )
    }

    private val screen by lazy { screenResolver.getScreen<Screens.Flow>(this) }

    private val tabScreenMap = LinkedHashMap<Int, Screen>().apply {
        put(R.id.menu_main_bottom_nav_home, TabScreen(Screens.Teams))
        put(R.id.menu_main_bottom_nav_catalog, TabScreen(Screens.LiveScore))
    }

    override fun invokeDi() {

        FlowComponent
            .create(this)
            .inject(this)
    }

    override fun prepareUi() {

        _viewBinding = ScreenFlowBinding.bind(findViewById(R.id.flow_root_container))

        viewBinding.mainBottomNavBar.apply {
            setOnNavigationItemSelectedListener {
                onTabScreenSelected(getTabScreen(it.itemId))
                false
            }
        }

        navigator.switchTo(getTabScreen(tabScreenMap.keys.first()))
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        bindNavigationContext()
    }

    private fun bindNavigationContext() {

        val builder =
            NavigationContext.Builder(this, navigationFactory)
                .screenSwitcher(screenSwitcher)
                .screenSwitchingListener { _, screenTo: Screen ->
                    val tabId = getTabId(screenTo)
                    viewBinding.mainBottomNavBar.menu.findItem(tabId).isChecked = true
                    bindNavigationContext()
                }
        val fragment = screenSwitcher.currentFragment
        if (fragment is ContainerIdProvider) {
            builder.fragmentNavigation(fragment.childFragmentManager, fragment.containerId)
        }

        navigationContextBinder.bind(builder.build())
    }

    private fun getTabScreen(tabId: Int) = tabScreenMap.getValue(tabId)

    private fun getTabId(screen: Screen): Int {
        for ((key, value) in tabScreenMap.entries) {
            val tabScreen = (value as TabScreen)
            if (screen == tabScreen || tabScreen.innerScreen == screen) {
                return key
            }
        }
        return -1
    }

    private fun onTabScreenSelected(tabScreen: Screen) = navigator.switchTo(tabScreen)

    override fun onPause() {
        navigationContextBinder.unbind(this)
        super.onPause()
    }

    override fun onDestroy() {
        _viewBinding = null
        super.onDestroy()
    }
}