package com.newscompany.news.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.newscompany.news.R
import com.newscompany.news.activity.WebViewActivity
import com.newscompany.news.model.NewsModel
import kotlinx.android.synthetic.main.item_layout.view.*

class NewsRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var newsList : List<NewsModel> = ArrayList()

    class NewsViewHolder (private val newsView : View) : RecyclerView.ViewHolder(newsView) {
        private val name : TextView = newsView.textName
        private val subtitle : TextView = newsView.textSubtitle
        private val image : ImageView = newsView.imageView

        fun bind(newsData : NewsModel) {
            name.text = newsData.name
            subtitle.text = newsData.subtitle
            Glide
                .with(newsView.context)
                .load(newsData.thumbnail)
                .centerCrop()
                .into(image)

            newsView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val intent = Intent(newsView.context, WebViewActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    intent.putExtra("title", newsData.name)
                    intent.putExtra("url", newsData.externalLink)
                    newsView.context.startActivity(intent)
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is NewsViewHolder -> {
                holder.bind(newsList[position])
            }
        }
    }
    fun submitList(newsList: List<NewsModel>) {
        this.newsList = newsList
    }
}