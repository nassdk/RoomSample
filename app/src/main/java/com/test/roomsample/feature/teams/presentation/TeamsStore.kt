package com.test.roomsample.feature.teams.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.test.roomsample.feature.teams.domain.model.TeamModel
import com.test.roomsample.feature.teams.presentation.TeamsStore.*

interface TeamsStore : Store<Intent, State, Label> {

    sealed class Intent {
        object Idle : Intent()
        data class LoadTeamList(val countryId: String) : Intent()
    }

    data class State(
        var loading: Boolean = false,
        var teamList: List<TeamModel> = emptyList()
    )

    sealed class Label {
        data class Error(val message: String) : Label()
    }
}