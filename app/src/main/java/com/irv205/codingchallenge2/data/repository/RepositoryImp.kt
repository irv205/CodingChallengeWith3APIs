package com.irv205.codingchallenge2.data.repository

import com.irv205.codingchallenge2.domain.DomainResponse
import com.irv205.codingchallenge2.domain.model.GenericModelDomain
import com.irv205.codingchallenge2.domain.repository.Repository
import com.irv205.codingchallenge2.domain.service.NetworkDataSource
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val networkDataSource: NetworkDataSource): Repository {

    override suspend fun getAnime(title: String): DomainResponse<List<GenericModelDomain>> {
        return try {
            networkDataSource.getAnime(title)
        } catch (e: Exception){
            DomainResponse.OnFailure(e.message.toString())
        }
    }

    override suspend fun getNews(title: String): DomainResponse<List<GenericModelDomain>> {
        return try {
            networkDataSource.getNews(title)
        } catch (e: Exception){
            DomainResponse.OnFailure(e.message.toString())
        }
    }

    override suspend fun getSimpsonQuotes(count: Int): DomainResponse<List<GenericModelDomain>> {
        return try {
            networkDataSource.getSimpson(count)
        } catch (e: Exception){
            DomainResponse.OnFailure(e.message.toString())
        }
    }
}