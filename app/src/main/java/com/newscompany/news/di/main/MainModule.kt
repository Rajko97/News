package com.newscompany.news.di.main

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.newscompany.news.network.main.MainApi
import com.newscompany.news.ui.main.adapter.NewsRecyclerAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object MainModule {
    @MainScope
    @Provides
    @JvmStatic
    fun provideMainApi(retrofit: Retrofit) : MainApi {
        return retrofit.create(MainApi::class.java)
    }
    @MainScope
    @Provides
    @JvmStatic
    fun provideGlideInstance(application: Application) : RequestManager {
        return Glide.with(application)
    }

    @MainScope
    @Provides
    @JvmStatic
    fun provideNewsRecyclerAdapter() : NewsRecyclerAdapter {return NewsRecyclerAdapter()}
}