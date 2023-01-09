package com.irv205.codingchallenge2.data.model

import com.google.gson.annotations.SerializedName

class SimpsonResponseDTO : ArrayList<SimpsonDTO>()

data class SimpsonDTO(
    @SerializedName("quote")
    val description: String,
    @SerializedName("character")
    val title: String,
    @SerializedName("image")
    val img: String
)