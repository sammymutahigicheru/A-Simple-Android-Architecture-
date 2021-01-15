package com.sammy.androidarchitecture.data.remote

import com.google.common.truth.Truth
import com.sammy.androidarchitecture.BaseTest
import com.sammy.androidarchitecture.utils.BaseTestDataSource.getResponse
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CharactersRemoteTestDatasource : BaseTest() {
    @InternalCoroutinesApi
    @Test
    fun `fetch All Characters`(){
        runBlocking {
            val response = getResponse{ charactersApiService.getAllCharacters() }
            Truth.assertThat(response.data).isNotEqualTo(null)
        }
    }
}