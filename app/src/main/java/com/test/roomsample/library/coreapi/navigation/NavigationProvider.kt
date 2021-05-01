package com.test.roomsample.library.coreapi.navigation

import me.aartikov.alligator.NavigationContextBinder
import me.aartikov.alligator.Navigator
import me.aartikov.alligator.ScreenResolver
import me.aartikov.alligator.navigationfactories.NavigationFactory

interface NavigationProvider {
    fun navigator(): Navigator
    fun navigationFactory(): NavigationFactory
    fun navigationContextBinder(): NavigationContextBinder
    fun screenResolver(): ScreenResolver
}