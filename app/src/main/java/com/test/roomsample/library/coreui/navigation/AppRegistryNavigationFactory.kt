package com.test.roomsample.library.coreui.navigation

import com.test.roomsample.feature.flow.FlowFragment
import com.test.roomsample.feature.live.LiveScoreFragment
import com.test.roomsample.feature.countries.presentation.CountriesFragment
import com.test.roomsample.feature.teams.TeamsFragment
import me.aartikov.alligator.navigationfactories.RegistryNavigationFactory

class AppRegistryNavigationFactory : RegistryNavigationFactory() {

    init {
        registerFragment(Screens.Flow::class.java, FlowFragment::class.java)
        registerFragment(Screens.LiveScore::class.java, LiveScoreFragment::class.java)
        registerFragment(Screens.CountryList::class.java, CountriesFragment::class.java)
        registerFragment(Screens.Teams::class.java, TeamsFragment::class.java)
    }
}