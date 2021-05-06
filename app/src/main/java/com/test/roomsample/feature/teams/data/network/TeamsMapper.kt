package com.test.roomsample.feature.teams.data.network

import com.test.roomsample.feature.teams.domain.model.TeamModel
import javax.inject.Inject

class TeamsMapper @Inject constructor() {

    fun map(model: TeamsResponseNet) = model.response.teams.map { team ->
        team.run {
            TeamModel(
                id = id,
                name = name
            )
        }
    }
}