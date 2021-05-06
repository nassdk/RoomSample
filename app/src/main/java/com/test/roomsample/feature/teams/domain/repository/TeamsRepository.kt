package com.test.roomsample.feature.teams.domain.repository

import com.test.roomsample.feature.teams.domain.model.TeamModel

interface TeamsRepository {
    suspend fun getTeams(country: String): List<TeamModel>
}