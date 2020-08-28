package com.example.rafaelanastacioalves.pokedex.repository.http

import com.example.rafaelanastacioalves.pokedex.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceGenerator {
    private val httpClient = OkHttpClient.Builder()
    private val logging = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(httpClient
                    .addInterceptor(logging)
                    .build())
            .addConverterFactory(GsonConverterFactory.create())
    private val retrofit = retrofitBuilder.build()
    fun <S> createService(serviceClass: Class<S>?): S {
        return retrofit
                .create(serviceClass)
    }
}