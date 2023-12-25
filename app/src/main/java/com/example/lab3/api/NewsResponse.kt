package com.example.lab3.api

data class NewsResponse(
    val news: List<News>
)

data class News(
    val title: String,
    val url: String,
    val description: String
)