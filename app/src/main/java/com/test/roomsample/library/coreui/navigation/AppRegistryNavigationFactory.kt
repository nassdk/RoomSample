package com.test.roomsample.library.coreui.navigation

import com.test.roomsample.feature.countries.presentation.CountriesFragment
import com.test.roomsample.feature.flow.FlowActivity
import com.test.roomsample.feature.live.presentation.LiveScoresFragment
import com.test.roomsample.feature.teams.TeamsFragment
import com.test.roomsample.library.coreui.tab.TabFragment
import com.test.roomsample.library.coreui.tab.TabScreen
import me.aartikov.alligator.navigationfactories.RegistryNavigationFactory

class AppRegistryNavigationFactory : RegistryNavigationFactory() {

    init {
        registerActivity(Screens.Flow::class.java, FlowActivity::class.java)
        registerFragment(TabScreen::class.java, TabFragment::class.java)
        registerFragment(Screens.LiveScore::class.java, LiveScoresFragment::class.java)
        registerFragment(Screens.CountryList::class.java, CountriesFragment::class.java)
        registerFragment(Screens.Teams::class.java, TeamsFragment::class.java)
    }
}