package com.test.roomsample.feature.teams.domain.usecase

import com.test.roomsample.feature.teams.domain.repository.TeamsRepository
import javax.inject.Inject

class LoadTeamsUseCase @Inject constructor(
    private val repository: TeamsRepository
) {
    suspend fun invoke(countryId: String) = repository.getTeams(country = countryId)
}