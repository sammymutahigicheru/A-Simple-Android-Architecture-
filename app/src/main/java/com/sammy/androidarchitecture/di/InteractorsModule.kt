package com.sammy.androidarchitecture.di

import com.sammy.androidarchitecture.interactors.characters_list.GetCharacters
import com.sammy.androidarchitecture.network.CharactersApiService
import com.sammy.androidarchitecture.network.mapper.ResultMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object InteractorsModule {
    @ViewModelScoped
    @Provides
    fun provideGetRecipes(
        apiService: CharactersApiService,
        mapper: ResultMapper
    ):GetCharacters{
        return GetCharacters(apiService,mapper)
    }
}