package com.irv205.codingchallenge2.domain.repository

import com.irv205.codingchallenge2.domain.DomainResponse
import com.irv205.codingchallenge2.domain.model.GenericModelDomain

interface Repository {
    suspend fun getAnime(title: String): DomainResponse<List<GenericModelDomain>>
    suspend fun getNews(title: String): DomainResponse<List<GenericModelDomain>>
    suspend fun getSimpsonQuotes(count: Int): DomainResponse<List<GenericModelDomain>>
}