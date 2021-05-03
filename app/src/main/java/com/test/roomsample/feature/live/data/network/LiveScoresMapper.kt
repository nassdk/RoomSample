package com.test.roomsample.feature.live.data.network

import com.test.roomsample.feature.live.domain.model.LiveScoreMatchModel
import javax.inject.Inject

class LiveScoresMapper @Inject constructor() {

    fun map(model: LiveScoreResponseNet) = model.response.matches.map { match ->
        match.run {
            LiveScoreMatchModel(
                location = location,
                score = score,
                time = time,
                status = status,
                homeTeam = homeTeam,
                guestTeam = guestTeam
            )
        }
    }
}