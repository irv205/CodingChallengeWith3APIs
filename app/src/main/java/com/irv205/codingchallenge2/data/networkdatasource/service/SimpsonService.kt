package com.irv205.codingchallenge2.data.networkdatasource.service

import com.irv205.codingchallenge2.data.model.SimpsonResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SimpsonService {

    @GET("quotes")
    suspend fun getSimpsonData(@Query("count") count: Int): SimpsonResponseDTO

}