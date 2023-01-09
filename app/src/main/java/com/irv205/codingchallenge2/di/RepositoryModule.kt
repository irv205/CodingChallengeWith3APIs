package com.irv205.codingchallenge2.di

import com.irv205.codingchallenge2.data.repository.RepositoryImp
import com.irv205.codingchallenge2.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideAnimeRepository(animeRepository: RepositoryImp): Repository
}