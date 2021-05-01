package com.test.roomsample.library.coreimpl.network.error

import com.google.gson.annotations.SerializedName

data class ErrorNetModel(
    @SerializedName("code") val code: String,
    @SerializedName("message") val message: String
)