package com.test.roomsample.feature.live.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.test.roomsample.feature.live.domain.model.LiveScoreMatchModel
import com.test.roomsample.feature.live.presentation.LiveScoresStore.*

interface LiveScoresStore : Store<Intent, State, Label> {

    sealed class Intent {
        object Idle : Intent()
        data class LoadLiveScores(val countryId: String) : Intent()
    }

    data class State(
        var loading: Boolean = false,
        var liveScores: List<LiveScoreMatchModel> = emptyList()
    )

    sealed class Label {
        data class Error(val message: String) : Label()
    }
}