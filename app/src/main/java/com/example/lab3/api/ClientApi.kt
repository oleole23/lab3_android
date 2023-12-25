package com.example.lab3.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClientApi {
    private val BASE_URL = "https://api.newsdata.io/"
    var pNewsApi: NewsApi? = null
    
    fun getNewsApi(): NewsApi {
        if (pNewsApi == null) {
            pNewsApi = Retrofit.Builder()
                .baseUrl(ClientApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsApi::class.java)
        }
        return pNewsApi!!
    }
}