package com.test.roomsample.feature.live.data.repository

import com.test.roomsample.feature.live.data.network.LiveScoresApi
import com.test.roomsample.feature.live.data.network.LiveScoresMapper
import com.test.roomsample.feature.live.domain.model.LiveScoreMatchModel
import com.test.roomsample.feature.live.domain.repository.LiveScoresRepository
import javax.inject.Inject

class LiveScoresRepositoryImpl @Inject constructor(
    private val api: LiveScoresApi,
    private val mapper: LiveScoresMapper
) : LiveScoresRepository {

    override suspend fun getLiveScores(countryId: String): List<LiveScoreMatchModel> = mapper.map(
        model = api.loadLiveScores(country = countryId)
    )
}