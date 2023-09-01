package com.example.news

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.Api.RetroInstance
import com.example.news.model.all.ArticleAll
import com.example.news.model.all.ListAll
import com.example.news.model.trend.Article
import com.example.news.model.trend.ListArticle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MyViewModel() : ViewModel() {


    private val service = RetroInstance.apiService


    private val trend = MutableLiveData<List<Article>>()
    val headlines: LiveData<List<Article>>
        get() = trend

    private val all = MutableLiveData<List<ArticleAll>>()
    val allData:LiveData<List<ArticleAll>>
        get() = all

    fun getHeadline()  {
        service.getTrend().enqueue(object : Callback<ListArticle> {
            override fun onResponse(call: Call<ListArticle>, response: Response<ListArticle>) {
                     val resp = response.body()
                     trend.value = resp!!.articles

            }

            override fun onFailure(call: Call<ListArticle>, t: Throwable) {
                Log.i("MYTAG",t.toString())
            }

        })
    }

    fun getAll() {
        service.allData().enqueue(object : Callback<ListAll> {
            override fun onResponse(call: Call<ListAll>, response: Response<ListAll>) {
                  val resp = response.body()
                  all.value = resp!!.articles

            }

            override fun onFailure(call: Call<ListAll>, t: Throwable) {
                Log.i("MYTAG",t.toString())
            }

        })
    }

}





