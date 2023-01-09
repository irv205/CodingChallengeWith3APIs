package com.irv205.codingchallenge2.data.model

import com.google.gson.annotations.SerializedName

class AnimeResponseDTO: ArrayList<AnimeDTO>()


data class AnimeDTO(
    @SerializedName("animeTitle")
    val title: String,
    @SerializedName("animeImg")
    val img: String,
    @SerializedName("status")
    val description: String
)

