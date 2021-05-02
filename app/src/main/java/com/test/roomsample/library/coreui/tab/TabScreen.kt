package com.test.roomsample.library.coreui.tab

import me.aartikov.alligator.Screen
import java.io.Serializable

data class TabScreen(
    val innerScreen: Screen,
) : Screen, Serializable

