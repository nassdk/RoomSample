package com.test.roomsample.library.coreimpl.network.interceptor

import com.test.roomsample.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class CredentialsInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("key", PRIVATE_API_KEY)
            .addQueryParameter("secret", PRIVATE_API_SECRET)

        val requestBuilder = originalRequest.newBuilder().url(newUrl.build())

        if (BuildConfig.DEBUG) {
            Timber.tag("REQUEST_URL_PRINT").e(newUrl.toString())
        }

        return chain.proceed(requestBuilder.build())
    }

    companion object {
        private const val PRIVATE_API_KEY = "bQAcD2k3pTOdy7L5"
        private const val PRIVATE_API_SECRET = "sRMU0egkJCKgndwy37LH96Ky0zMyHxEa"
    }
}