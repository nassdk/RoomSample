package com.test.roomsample.feature.live.domain.repository

import com.test.roomsample.feature.live.domain.model.LiveScoreMatchModel

interface LiveScoresRepository {
    suspend fun getLiveScores(countryId: String): List<LiveScoreMatchModel>
}