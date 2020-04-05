package com.newscompany.news.model.main

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RequestNewsModel(
    @SerializedName("status")
    @Expose
    var status : String,

    @SerializedName("totalResults")
    @Expose
    var total : Int,

    @SerializedName("articles")
    @Expose
    var data : List<NewsData>

) {
    data class NewsData (
        @SerializedName("title")
        @Expose
        var name : String,

        @SerializedName("description")
        @Expose
        var subtitle : String,

        @SerializedName("url")
        @Expose
        var externalLink : String,

        @SerializedName("urlToImage")
        @Expose
        var thumbnail:String
    )
}