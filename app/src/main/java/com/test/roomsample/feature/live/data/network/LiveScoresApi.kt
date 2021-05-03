package com.test.roomsample.feature.live.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface LiveScoresApi {

    @GET("scores/live.json")
    suspend fun loadLiveScores(
        @Query("country") country: String
    ): LiveScoreResponseNet
}