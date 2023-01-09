package com.irv205.codingchallenge2.data.networkdatasource.service

import com.irv205.codingchallenge2.data.model.AnimeResponseDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeService {

    @GET("search")
    suspend fun getAnimeData(
        @Query("keyw") keyw: String
    ): AnimeResponseDTO

}