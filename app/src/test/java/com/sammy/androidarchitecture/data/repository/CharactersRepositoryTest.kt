package com.sammy.androidarchitecture.data.repository

import com.google.common.truth.Truth
import com.sammy.androidarchitecture.BaseTest
import com.sammy.androidarchitecture.utils.BaseTestDataSource
import junit.framework.TestCase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CharactersRepositoryTest : BaseTest() {
    private lateinit var charactersRepository: CharactersRepository
    override fun setUp() {
        super.setUp()
        charactersRepository = CharactersRepository(charactersApiService)
    }
    @InternalCoroutinesApi
    @Test
    fun `fetch All Characters`(){
        runBlocking {
            val response = charactersRepository.getCharacters()
            response.collect {
                Truth.assertThat(response).isNotNull()
            }
        }
    }
}