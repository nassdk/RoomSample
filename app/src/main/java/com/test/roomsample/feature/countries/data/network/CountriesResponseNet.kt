package com.test.roomsample.feature.countries.data.network

import com.google.gson.annotations.SerializedName

data class CountriesResponseNet(
    @SerializedName("data") val data: CountriesNetModel
) {
    data class CountriesNetModel(
        @SerializedName("country") val countries: List<CountryNetModel>
    )
}