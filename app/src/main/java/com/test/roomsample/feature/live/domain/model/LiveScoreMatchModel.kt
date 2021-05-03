package com.test.roomsample.feature.live.domain.model

data class LiveScoreMatchModel(
    val location: String,
    val score: String,
    val time: String,
    val status: String,
    val homeTeam: String,
    val guestTeam: String
)