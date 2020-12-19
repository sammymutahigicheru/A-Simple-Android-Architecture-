package com.sammy.androidarchitecture.data.remote

import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
        private val charactersApiService: CharactersApiService
):BaseDataSource(){

    suspend fun getAllCharacters() = getResponse { charactersApiService.getAllCharacters() }

}