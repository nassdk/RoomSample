package com.test.roomsample.library.coreimpl.network.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.test.roomsample.library.coreimpl.network.connection.NetworkChecking
import com.test.roomsample.library.coreimpl.network.interceptor.CredentialsInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class NetworkModule {

    companion object {

        @Provides
        @Singleton
        fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        @Provides
        @Singleton
        fun provideOkHttp3(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(CredentialsInterceptor())
                .addInterceptor(loggingInterceptor)
                .build()

        @Provides
        @Singleton
        fun provideGson(): Gson = GsonBuilder()
            .setLenient()
            .create()

        @Provides
        @Singleton
        fun provideNetworkChecker(context: Context): NetworkChecking =
            NetworkChecking(context = context)

        @Provides
        @Singleton
        fun provideRetrofit(
            client: OkHttpClient,
            gson: Gson
        ): Retrofit =
            Retrofit.Builder()
                .baseUrl("https://livescore-api.com/api-client/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }
}