package com.example.news.model.all


import com.google.gson.annotations.SerializedName

data class SourceAll(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)