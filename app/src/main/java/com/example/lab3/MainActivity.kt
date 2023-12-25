package com.example.lab3

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3.api.ClientApi
import com.example.lab3.api.News
import com.example.lab3.api.NewsApi
import com.example.lab3.api.NewsResponse
import com.example.lab3.recycler_view.Adapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity: AppCompatActivity() {
    val ApiKey = "pub_35393dd298f6b959f36b4740ddb3e25ebcfe2"
    private lateinit var adapter: Adapter
    private lateinit var newsApi: NewsApi
    private var news: List<News> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val recyclerView: RecyclerView = findViewById(R.id.NewsList)
        val manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = manager
        adapter = Adapter(news)
        recyclerView.adapter = adapter

        newsApi = ClientApi.pNewsApi!!
    }

    fun search(view: View?) {
        val etWords = findViewById<EditText>(R.id.search_bar)
        val keyWord = etWords.text.toString()
        if (keyWord.isNotEmpty()) {
            searchNews(keyWord)
        }
    }

    private fun searchNews(keyWord: String) {
        val req = newsApi.getNews(ApiKey, keyWord)

        req.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                if (response.isSuccessful) {
                    val newsList = response.body()
                    if (newsList != null) {
                        val news = newsList.news
                        adapter.updateNewsList(news)
                    }
                } else {
                    Toast.makeText(this@MainActivity, "API ERROR", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "NETWORK ERROR", Toast.LENGTH_SHORT).show()
            }
        })

    }
}