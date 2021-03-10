package com.sammy.androidarchitecture.data.repository

import com.sammy.androidarchitecture.data.mappers.toCharcters
import com.sammy.androidarchitecture.network.model.Character
import com.sammy.androidarchitecture.data.remote.CharactersApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersRepository @Inject constructor(private val charactersApiService: CharactersApiService) {
    suspend fun getCharacters(): Flow<List<Character>> = flow {
        val response = charactersApiService.getCharacters()
        val characters = mutableListOf<Character>()
        for (character in response.results){
            characters.add(character.toCharcters())
        }
        emit(characters)
    }
}