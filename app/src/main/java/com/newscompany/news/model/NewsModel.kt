package com.newscompany.news.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsModel(
    @SerializedName("name")
    @Expose
    var name : String?,
    @SerializedName("subtitle")
    @Expose
    var subtitle : String?,
    @SerializedName("external_link")
    @Expose
    var externalLink : String?,
    @SerializedName("thumbnail")
    @Expose
    var thumbnail:String?
)