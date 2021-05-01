package com.test.roomsample.feature.countries.presentation.ui.toolbar

import com.test.roomsample.feature.countries.presentation.CountriesStore.Intent
import com.test.roomsample.feature.countries.presentation.CountriesStore.State
import com.test.roomsample.feature.countries.presentation.ui.toolbar.CountriesToolbarView.Event
import com.test.roomsample.feature.countries.presentation.ui.toolbar.CountriesToolbarView.Model
import com.test.roomsample.library.coreui.mvi.ViewConnections

object CountriesToolbarConnections : ViewConnections<State, Intent, Model, Event> {

    override val stateToModel: (State) -> Model = {
        Model()
    }

    override val eventToIntent: (Event) -> Intent = { event ->
        when (event) {
            is Event.Search -> Intent.SearchQueryChange(query = event.query)
        }
    }
}