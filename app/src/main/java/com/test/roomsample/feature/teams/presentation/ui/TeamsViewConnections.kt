package com.test.roomsample.feature.teams.presentation.ui

import com.test.roomsample.feature.teams.presentation.TeamsStore.Intent
import com.test.roomsample.feature.teams.presentation.TeamsStore.State
import com.test.roomsample.feature.teams.presentation.ui.TeamsMainView.Event
import com.test.roomsample.feature.teams.presentation.ui.TeamsMainView.Model
import com.test.roomsample.library.coreui.mvi.ViewConnections

object TeamsViewConnections : ViewConnections<State, Intent, Model, Event> {
    override val stateToModel: (State) -> Model = { state ->
        Model(
            loading = state.loading,
            teamsList = state.teamList
        )
    }

    override val eventToIntent: (Event) -> Intent = { _ ->
        Intent.Idle
    }
}