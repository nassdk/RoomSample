package com.test.roomsample.feature.teams.data.network

import com.google.gson.annotations.SerializedName

data class TeamNet(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)