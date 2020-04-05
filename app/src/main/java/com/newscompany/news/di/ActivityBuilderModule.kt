package com.newscompany.news.di

import com.newscompany.news.ui.main.MainActivity
import com.newscompany.news.ui.web_view.WebViewActivity
import com.newscompany.news.di.main.MainModule
import com.newscompany.news.di.main.MainScope
import com.newscompany.news.di.web_view.WebViewScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @MainScope
    @ContributesAndroidInjector( modules = [
        MainModule::class
    ])
    abstract fun contributeMainActivity() : MainActivity

    @WebViewScope
    @ContributesAndroidInjector
    abstract fun contributeWebVuewActivity() : WebViewActivity
}