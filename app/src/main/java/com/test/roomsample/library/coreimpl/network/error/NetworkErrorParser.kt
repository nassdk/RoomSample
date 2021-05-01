package com.test.roomsample.library.coreimpl.network.error

import com.google.gson.Gson
import javax.inject.Inject

class NetworkErrorParser @Inject constructor(
    private val gson: Gson
) {

    fun parseError(response: String?): ErrorNetModel? = try {
        gson.fromJson(response, ErrorNetModel::class.java)
    } catch (e: Exception) {
        null
    }
}