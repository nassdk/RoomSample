package com.test.roomsample.feature.countries.presentation.ui.toolbar

import com.arkivanov.mvikotlin.core.view.MviView
import com.test.roomsample.feature.countries.presentation.ui.toolbar.CountriesToolbarView.Event
import com.test.roomsample.feature.countries.presentation.ui.toolbar.CountriesToolbarView.Model

interface CountriesToolbarView : MviView<Model, Event> {

    class Model

    sealed class Event {
        data class Search(val query: String) : Event()
    }
}