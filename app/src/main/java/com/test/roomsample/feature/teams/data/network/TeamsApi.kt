package com.test.roomsample.feature.teams.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface TeamsApi {

    @GET("teams/list.json")
    suspend fun loadTeams(
        @Query("country_id") country: String
    ): TeamsResponseNet
}