package com.newscompany.news.ui.main


import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.newscompany.news.R
import com.newscompany.news.model.main.RequestNewsModel
import com.newscompany.news.network.main.MainApi
import com.newscompany.news.ui.main.adapter.NewsRecyclerAdapter
import com.newscompany.news.util.Constants
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {
    private var news: List<RequestNewsModel.NewsData>? = ArrayList()

    @Inject
    lateinit var newsAdapter: NewsRecyclerAdapter

    @Inject
    lateinit var mainApi: MainApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadJson()
    }

    private fun loadJson() {
            mainApi.getTopHeadlines(apiKey = Constants.API_KEY).enqueue(object : Callback<RequestNewsModel> {
                override fun onFailure(call: Call<RequestNewsModel>, t: Throwable) {
                }

                override fun onResponse(call: Call<RequestNewsModel>, response: Response<RequestNewsModel>) {
                    news = response.body()?.data
                    initRecyclerView()
                }
            })
    }

    private fun initRecyclerView() {
        mRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            news?.let { newsAdapter.submitList(it) }
            adapter = newsAdapter
        }
    }
}