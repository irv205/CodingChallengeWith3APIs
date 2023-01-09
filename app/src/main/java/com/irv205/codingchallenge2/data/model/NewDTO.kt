package com.irv205.codingchallenge2.data.model

import com.google.gson.annotations.SerializedName

class NewsDTO(
    @SerializedName("articles")
    val articles: List<ArticleDTO>
)

data class ArticleDTO(
    @SerializedName("imageUrl")
    val img: String?,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String
)