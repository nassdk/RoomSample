package com.test.roomsample.library.coreui.navigation

import me.aartikov.alligator.Screen
import java.io.Serializable

object Screens {
    data class Flow(val countryId: String) : Screen, Serializable
    data class LiveScore(val countryId: String) : Screen, Serializable
    data class Teams(val countryId: String) : Screen, Serializable
    object CountryList : Screen, Serializable
}