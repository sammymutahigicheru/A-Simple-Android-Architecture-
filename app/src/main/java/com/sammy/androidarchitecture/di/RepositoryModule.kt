package com.sammy.androidarchitecture.di

import com.sammy.androidarchitecture.data.remote.CharacterRemoteDataSource
import com.sammy.androidarchitecture.data.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: CharacterRemoteDataSource) =
            CharacterRepository(remoteDataSource)
}