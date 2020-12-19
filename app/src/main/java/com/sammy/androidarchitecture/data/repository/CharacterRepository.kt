package com.sammy.androidarchitecture.data.repository

import com.sammy.androidarchitecture.commons.performGetOperation
import com.sammy.androidarchitecture.data.remote.CharactersDataSource
import javax.inject.Inject

class CharacterRepository @Inject constructor(
        private val charactersDataSource: CharactersDataSource
) {
    fun getCharacters() = performGetOperation(
            networkCall = {charactersDataSource.getAllCharacters()}
    )
}