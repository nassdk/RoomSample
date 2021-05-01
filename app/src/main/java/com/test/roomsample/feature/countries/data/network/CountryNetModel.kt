package com.test.roomsample.feature.countries.data.network

import com.google.gson.annotations.SerializedName

data class CountryNetModel(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)