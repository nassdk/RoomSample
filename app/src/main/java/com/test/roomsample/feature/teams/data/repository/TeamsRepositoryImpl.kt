package com.test.roomsample.feature.teams.data.repository

import com.test.roomsample.feature.teams.data.network.TeamsApi
import com.test.roomsample.feature.teams.data.network.TeamsMapper
import com.test.roomsample.feature.teams.domain.model.TeamModel
import com.test.roomsample.feature.teams.domain.repository.TeamsRepository
import javax.inject.Inject

class TeamsRepositoryImpl @Inject constructor(
    private val api: TeamsApi,
    private val teamsMapper: TeamsMapper
) : TeamsRepository {

    override suspend fun getTeams(country: String): List<TeamModel> =
        teamsMapper.map(model = api.loadTeams(country = country))
}