package com.test.roomsample.feature.live.data.network

import com.google.gson.annotations.SerializedName

data class LiveScoreMatchNet(
    @SerializedName(value = "location") val location: String,
    @SerializedName(value = "score") val score: String,
    @SerializedName(value = "time") val time: String,
    @SerializedName(value = "status") val status: String,
    @SerializedName(value = "home_name") val homeTeam: String,
    @SerializedName(value = "away_name") val guestTeam: String
)