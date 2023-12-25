package com.example.lab3.recycler_view

import com.example.lab3.R
import com.example.lab3.api.News

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class Adapter(private var newsList: List<News>) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.Title)
        val tvDescription: TextView = view.findViewById(R.id.Description)
        val tvUrl: TextView = view.findViewById(R.id.Url)
    }

    override fun onCreateViewHolder(vGroup: ViewGroup, vType: Int): ViewHolder {
        val view = LayoutInflater.from(vGroup.context).inflate(R.layout.news_recycler_view, vGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = newsList[position]
        holder.tvTitle.text = news.title
        holder.tvDescription.text = news.description
        holder.tvUrl.text = news.url
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun updateNewsList(newNewsList: List<News>) {
        newsList = newNewsList
        notifyDataSetChanged()
    }
}
