package com.test.roomsample.feature.flow

import androidx.appcompat.app.AppCompatActivity
import com.test.roomsample.R
import com.test.roomsample.databinding.ScreenFlowBinding
import com.test.roomsample.library.coreui.base.BaseFragment
import com.test.roomsample.library.coreui.navigation.ContainerIdProvider
import com.test.roomsample.library.coreui.navigation.Screens
import com.test.roomsample.library.coreui.tab.TabScreen
import me.aartikov.alligator.*
import me.aartikov.alligator.listeners.ScreenSwitchingListener
import me.aartikov.alligator.navigationfactories.NavigationFactory
import me.aartikov.alligator.screenswitchers.FragmentScreenSwitcher
import javax.inject.Inject

class FlowFragment : BaseFragment(R.layout.screen_flow) {

    private var _viewBinding: ScreenFlowBinding? = null
    private val viewBinding: ScreenFlowBinding get() = _viewBinding!!

    @Inject lateinit var navigator: Navigator
    @Inject lateinit var navigationFactory: NavigationFactory
    @Inject lateinit var navigationContextBinder: NavigationContextBinder
    @Inject lateinit var screenResolver: ScreenResolver

    private val screenSwitcher by lazy {
        FragmentScreenSwitcher(
            navigationFactory,
            requireActivity().supportFragmentManager,
            R.id.main_container
        )
    }

    private val screen by lazy { screenResolver.getScreen<Screens.Flow>(this) }

    private val tabScreenMap = LinkedHashMap<Int, Screen>().apply {
        put(R.id.menu_main_bottom_nav_home, TabScreen(Screens.Teams))
        put(R.id.menu_main_bottom_nav_catalog, TabScreen(Screens.LiveScore))
//        put(R.id.menu_main_bottom_nav_cart, TabScreen(CartScreens.Cart))
    }

    override fun invokeDi() {

        FlowComponent
            .create(requireActivity())
            .inject(this)
    }

    override fun initBindings() {
        _viewBinding = ScreenFlowBinding.bind(requireView())
    }

    override fun prepareUi() {

        viewBinding.mainBottomNavBar.apply {
            setOnNavigationItemSelectedListener {
                onTabScreenSelected(getTabScreen(it.itemId))
                false
            }
        }

        navigator.switchTo(getTabScreen(tabScreenMap.keys.first()))
    }

    override fun onResume() {
        super.onResume()
        bindNavigationContext()
    }

    private fun bindNavigationContext() {

        val builder =
            NavigationContext.Builder(requireActivity() as AppCompatActivity, navigationFactory)
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

    private fun getTabScreen(tabId: Int): Screen = tabScreenMap.getValue(tabId)

    private fun getTabId(screen: Screen): Int {
        for ((key, value) in tabScreenMap.entries) {
            val tabScreen = (value as TabScreen)
            if (screen == tabScreen || tabScreen.innerScreen == screen) {
                return key
            }
        }
        return -1
    }

    private fun onTabScreenSelected(tabScreen: Screen) {
        navigator.switchTo(tabScreen)
    }

    override fun onPause() {
        navigationContextBinder.unbind(requireActivity() as AppCompatActivity?)
        super.onPause()
    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
}