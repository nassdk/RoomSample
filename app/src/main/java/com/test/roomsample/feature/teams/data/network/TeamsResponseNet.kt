package com.test.roomsample.feature.teams.data.network

import com.google.gson.annotations.SerializedName

data class TeamsResponseNet(
    @SerializedName("data") val response: TeamsNetModel
) {
    data class TeamsNetModel(
        @SerializedName("teams") val teams: List<TeamNet>
    )
}