package com.irv205.codingchallenge2.di

import com.irv205.codingchallenge2.data.networkdatasource.NetworkDatSourceImp
import com.irv205.codingchallenge2.domain.service.NetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun provideAnimeNetworkSource(
        animeNetworkDatSourceImp: NetworkDatSourceImp): NetworkDataSource
}