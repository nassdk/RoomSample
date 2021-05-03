package com.test.roomsample.feature.live.presentation.ui

import com.test.roomsample.feature.live.presentation.LiveScoresStore.Intent
import com.test.roomsample.feature.live.presentation.LiveScoresStore.State
import com.test.roomsample.feature.live.presentation.ui.LiveScoresMainView.Event
import com.test.roomsample.feature.live.presentation.ui.LiveScoresMainView.Model
import com.test.roomsample.library.coreui.mvi.ViewConnections

object LiveScoresViewConnections : ViewConnections<State, Intent, Model, Event> {

    override val stateToModel: (State) -> Model = { state ->
        Model(
            loading = state.loading,
            liveScores = state.liveScores
        )
    }

    override val eventToIntent: (Event) -> Intent = { _ ->
        Intent.Idle
    }
}