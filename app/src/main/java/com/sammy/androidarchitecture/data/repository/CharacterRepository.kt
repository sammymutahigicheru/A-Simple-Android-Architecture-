package com.sammy.androidarchitecture.data.repository

import com.sammy.androidarchitecture.commons.performGetOperation
import com.sammy.androidarchitecture.data.remote.CharacterRemoteDataSource
import javax.inject.Inject

class CharacterRepository @Inject constructor(
        private val charactersDataSource: CharacterRemoteDataSource
) {
    fun getCharacters() = performGetOperation(
            networkCall = {charactersDataSource.getAllCharacters()}
    )
    fun getCharacterById(id:Int) = performGetOperation(
        networkCall = {charactersDataSource.getCharacterById(id)}
    )
}