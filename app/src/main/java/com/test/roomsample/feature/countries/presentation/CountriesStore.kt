package com.test.roomsample.feature.countries.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.test.roomsample.feature.countries.domain.model.CountryModel
import com.test.roomsample.feature.countries.presentation.CountriesStore.*

interface CountriesStore : Store<Intent, State, Label> {

    sealed class Intent {
        data class CountrySelected(val countryId: String) : Intent()
        data class SearchQueryChange(val query: String) : Intent()
    }

    data class State(
        val countries: List<CountryModel> = emptyList(),
        val loading: Boolean = false
    )

    sealed class Label {
        data class Error(val message: String) : Label()
    }
}