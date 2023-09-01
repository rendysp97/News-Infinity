package com.example.news.model.all


import com.google.gson.annotations.SerializedName

data class ListAll(
    @SerializedName("articles")
    val articles: List<ArticleAll>,

    )