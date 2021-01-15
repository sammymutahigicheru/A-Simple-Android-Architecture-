package com.sammy.androidarchitecture.data.remote

import com.sammy.androidarchitecture.BaseTest
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CharactersRemoteTestDatasource : BaseTest() {
    private lateinit var datasource: CharacterRemoteDataSource
    @Before
    fun setUp(){
        super.setUp()
        datasource = CharacterRemoteDataSource(charactersApiService)
    }
    @InternalCoroutinesApi
    @Test
    fun fetchCharacters(){
        runBlocking {

        }
    }
}