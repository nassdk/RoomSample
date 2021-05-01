package com.test.roomsample.feature.countries.data.network

import retrofit2.http.GET

interface CountriesApi {

    @GET("countries/list.json")
    suspend fun loadCountriesModel(): CountriesResponseNet
}