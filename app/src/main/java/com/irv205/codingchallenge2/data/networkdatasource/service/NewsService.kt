package com.irv205.codingchallenge2.data.networkdatasource.service

import com.irv205.codingchallenge2.data.model.NewsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("all")
    suspend fun getNewsData(
        @Query("apiKey") apiKey: String,
        @Query("title") title: String,
        @Query("showReprints") showReprints: Boolean
    ) : NewsDTO

}