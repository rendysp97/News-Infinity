package com.example.news.Api

import com.example.news.model.all.ListAll
import com.example.news.model.trend.ListArticle
import retrofit2.Call
import retrofit2.http.GET

interface Service {

    @GET("everything?domains=wsj.com")
    fun allData() : Call<ListAll>

    @GET("top-headlines?sources=techcrunch")
    fun getTrend() : Call<ListArticle>

}


