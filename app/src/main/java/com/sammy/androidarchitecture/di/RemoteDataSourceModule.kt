package com.sammy.androidarchitecture.di

import com.sammy.androidarchitecture.data.remote.CharacterRemoteDataSource
import com.sammy.androidarchitecture.data.remote.CharactersApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RemoteDataSourceModule{
    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(characterService: CharactersApiService) = CharacterRemoteDataSource(characterService)
}