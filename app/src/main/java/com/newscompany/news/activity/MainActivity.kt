package com.newscompany.news.activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import com.newscompany.news.model.NewsModel
import com.newscompany.news.R
import com.newscompany.news.adapter.NewsRecyclerAdapter
import com.newscompany.news.network.GetNewsService
import com.newscompany.news.network.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {
    private var news : ArrayList<NewsModel>? = ArrayList()
    private lateinit var newsAdapter : NewsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createCustomActionBar()
        loadJson()
    }

    private fun createCustomActionBar() {
        val view = layoutInflater.inflate(R.layout.action_bar, null)
        val params = ActionBar.LayoutParams(
            ActionBar.LayoutParams.WRAP_CONTENT,
            ActionBar.LayoutParams.MATCH_PARENT,
            Gravity.CENTER
        )
        val title = view.findViewById<TextView>(R.id.actionbar_title)
        title.text = getString(R.string.action_bar_title)
        supportActionBar?.setCustomView(view, params)
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun loadJson() {
        val service = RetrofitClientInstance.retrofitInstance?.create(GetNewsService::class.java)
        val call = service?.getAllNews()
        call?.enqueue(object : Callback<JsonArray> {
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                val listType = object : TypeToken<List<NewsModel?>?>() {}.type
                news = getNewsListFromJson(response.body().toString(), listType)
                initRecyclerView()
            }
            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                Toast.makeText(applicationContext, "Err: something went wrong", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun initRecyclerView() {
        mRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            newsAdapter = NewsRecyclerAdapter()
            news?.let { newsAdapter.submitList(it) }
            adapter = newsAdapter

        }

    }

    companion object {
        fun getNewsListFromJson(jsonString: String?, type: Type?) : ArrayList<NewsModel>? {
            return if (!isValid(jsonString)) {
                null
            } else Gson().fromJson(jsonString, type)
        }
        private fun isValid(json: String?) : Boolean {
            return try {
                JsonParser().parse(json)
                true
            } catch (jse: JsonSyntaxException) {
                false
            }
        }
    }
}