package com.lk.popularfilms2019.net

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {

    private const val BASE_URL = "https://api.themoviedb.org/4/"

    private val logging = HttpLoggingInterceptor()

    private val client = OkHttpClient.Builder()

    val instance: JSONPlaceHolderApi by lazy {

        logging.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(
            logging
        )

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(client.build())
            .build()

        retrofit.create(JSONPlaceHolderApi::class.java)
    }

}