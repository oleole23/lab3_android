package com.example.lab3.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("news")
    fun getNews(
        @Query("query") query: String,
        @Query("apiKey") apiKey: String
    ): Call<NewsResponse>
}