package com.test.roomsample.feature.countries.presentation.ui.main

import com.arkivanov.mvikotlin.core.view.MviView
import com.test.roomsample.feature.countries.domain.model.CountryModel
import com.test.roomsample.feature.countries.presentation.ui.main.CountriesMainView.Event
import com.test.roomsample.feature.countries.presentation.ui.main.CountriesMainView.Model

interface CountriesMainView : MviView<Model, Event> {

    data class Model(
        val loading: Boolean,
        val countries: List<CountryModel>,
        val filteredCountries: List<CountryModel>
    )

    sealed class Event {
        data class CountrySelected(val id: String) : Event()
    }
}