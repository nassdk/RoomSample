package com.test.roomsample.feature.teams.presentation.ui

import com.arkivanov.mvikotlin.core.view.MviView
import com.test.roomsample.feature.teams.domain.model.TeamModel
import com.test.roomsample.feature.teams.presentation.ui.TeamsMainView.Event
import com.test.roomsample.feature.teams.presentation.ui.TeamsMainView.Model

interface TeamsMainView : MviView<Model, Event> {

    data class Model(
        val loading: Boolean,
        val teamsList: List<TeamModel>
    )

    class Event
}