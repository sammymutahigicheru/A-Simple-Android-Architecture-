package com.sammy.androidarchitecture.data.remote

import com.sammy.androidarchitecture.network.response.CharacterResponse
import com.sammy.androidarchitecture.network.model.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharactersApiService {
    @GET("character")
    suspend fun getAllCharacters():Response<CharacterResponse>
    @GET("character")
    suspend fun getCharacters(): CharacterResponse
    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<Result>
}