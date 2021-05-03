package com.test.roomsample.feature.live.presentation.ui

import com.arkivanov.mvikotlin.core.view.MviView
import com.test.roomsample.feature.live.domain.model.LiveScoreMatchModel
import com.test.roomsample.feature.live.presentation.ui.LiveScoresMainView.Event
import com.test.roomsample.feature.live.presentation.ui.LiveScoresMainView.Model

interface LiveScoresMainView : MviView<Model, Event> {

    data class Model(
        val loading: Boolean,
        val liveScores: List<LiveScoreMatchModel>
    )

    class Event
}