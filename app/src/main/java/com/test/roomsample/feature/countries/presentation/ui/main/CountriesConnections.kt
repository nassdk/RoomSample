package com.test.roomsample.feature.countries.presentation.ui.main

import com.test.roomsample.feature.countries.presentation.CountriesStore.Intent
import com.test.roomsample.feature.countries.presentation.CountriesStore.State
import com.test.roomsample.feature.countries.presentation.ui.main.CountriesMainView.Event
import com.test.roomsample.feature.countries.presentation.ui.main.CountriesMainView.Model
import com.test.roomsample.library.coreui.mvi.ViewConnections

object CountriesConnections : ViewConnections<State, Intent, Model, Event> {

    override val stateToModel: (State) -> Model = { state ->
        Model(
            loading = state.loading,
            countries = state.allCountries,
            filteredCountries = state.filteredCountries
        )
    }

    override val eventToIntent: (Event) -> Intent = { event ->
        when (event) {
            is Event.CountrySelected -> Intent.CountrySelected(countryId = event.id)
        }
    }
}