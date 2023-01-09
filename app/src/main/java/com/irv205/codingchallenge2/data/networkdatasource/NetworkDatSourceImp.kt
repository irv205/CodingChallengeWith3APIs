package com.irv205.codingchallenge2.data.networkdatasource

import com.irv205.codingchallenge2.BuildConfig
import com.irv205.codingchallenge2.data.mapper.toDomainModel
import com.irv205.codingchallenge2.data.networkdatasource.service.AnimeService
import com.irv205.codingchallenge2.data.networkdatasource.service.NewsService
import com.irv205.codingchallenge2.data.networkdatasource.service.SimpsonService
import com.irv205.codingchallenge2.domain.DomainResponse
import com.irv205.codingchallenge2.domain.model.GenericModelDomain
import com.irv205.codingchallenge2.domain.service.NetworkDataSource
import javax.inject.Inject

class NetworkDatSourceImp @Inject constructor(
    private val animeService: AnimeService,
    private val newsService: NewsService,
    private val simpsonService: SimpsonService
    ): NetworkDataSource {

    override suspend fun getAnime(title: String): DomainResponse<List<GenericModelDomain>> {
        val response = animeService.getAnimeData(title)
        return DomainResponse.Success(response.map {
            toDomainModel(
                it.img,
                it.title,
                it.description)
        })
    }

    override suspend fun getNews(title: String): DomainResponse<List<GenericModelDomain>> {
        val response = newsService.getNewsData(BuildConfig.API_KEY_NEWS,title, false)
        return DomainResponse.Success(response.articles.map {
            toDomainModel(
                it.img,
                it.title,
                it.description
            )
        })
    }

    override suspend fun getSimpson(count: Int): DomainResponse<List<GenericModelDomain>> {
        val response = simpsonService.getSimpsonData(count)
        return DomainResponse.Success(response.map {
            toDomainModel(
                it.img,
                it.title,
                it.description
            )
        })
    }
}