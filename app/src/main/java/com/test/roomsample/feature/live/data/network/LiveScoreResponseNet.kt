package com.test.roomsample.feature.live.data.network

import com.google.gson.annotations.SerializedName

data class LiveScoreResponseNet(
    @SerializedName(value = "data") val response: LiveScoresNet
) {
    data class LiveScoresNet(
        @SerializedName(value = "match") val matches: List<LiveScoreMatchNet>
    )
}