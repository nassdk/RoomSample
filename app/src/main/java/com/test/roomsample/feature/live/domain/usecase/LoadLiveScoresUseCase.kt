package com.test.roomsample.feature.live.domain.usecase

import com.test.roomsample.feature.live.domain.model.LiveScoreMatchModel
import com.test.roomsample.feature.live.domain.repository.LiveScoresRepository
import javax.inject.Inject

class LoadLiveScoresUseCase @Inject constructor(
    private val repository: LiveScoresRepository
) {
    suspend fun invoke(countryId: String): List<LiveScoreMatchModel> =
        repository.getLiveScores(countryId = countryId)
}