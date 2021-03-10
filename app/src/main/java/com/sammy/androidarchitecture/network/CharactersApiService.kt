package com.sammy.androidarchitecture.network

import com.sammy.androidarchitecture.network.response.CharacterResponse
import com.sammy.androidarchitecture.network.model.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharactersApiService {
    @GET("character")
    suspend fun getAllCharacters():CharacterResponse
    @GET("character")
    suspend fun getCharacters(): CharacterResponse
    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Result
}