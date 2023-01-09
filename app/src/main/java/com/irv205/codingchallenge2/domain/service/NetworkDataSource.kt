package com.irv205.codingchallenge2.domain.service

import com.irv205.codingchallenge2.domain.DomainResponse
import com.irv205.codingchallenge2.domain.model.GenericModelDomain

interface NetworkDataSource {

    suspend fun getAnime(title: String): DomainResponse<List<GenericModelDomain>>
    suspend fun getNews(title: String): DomainResponse<List<GenericModelDomain>>
    suspend fun getSimpson(count: Int): DomainResponse<List<GenericModelDomain>>
}