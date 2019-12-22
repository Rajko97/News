package com.newscompany.news.network

import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.GET

interface GetNewsService {
    @GET("api/app/article_types/5729fc387fdea7e267fa9761")
    fun getAllNews() : Call<JsonArray>
}