package com.sammy.androidarchitecture.data.remote

import com.sammy.androidarchitecture.data.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET

interface CharactersApiService {
    @GET("character")
    suspend fun getAllCharacters():Response<CharacterResponse>
}