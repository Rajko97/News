package com.newscompany.news.network.main

import com.newscompany.news.model.main.RequestNewsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {
    @GET("/v2/top-headlines")
    fun getTopHeadlines(
        @Query("country") country : String = "us",
        @Query("apiKey") apiKey : String)
            :Call<RequestNewsModel>
}