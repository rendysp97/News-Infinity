package com.example.news.model.trend


import com.google.gson.annotations.SerializedName

data class ListArticle(
    @SerializedName("articles")
    val articles: List<Article>,
)