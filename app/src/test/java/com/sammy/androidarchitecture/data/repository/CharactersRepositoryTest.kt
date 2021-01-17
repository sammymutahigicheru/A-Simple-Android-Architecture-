package com.sammy.androidarchitecture.data.repository

import com.google.common.truth.Truth
import com.sammy.androidarchitecture.data.remote.CharactersApiService
import com.sammy.androidarchitecture.utils.FileReader
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@HiltAndroidTest
class CharactersRepositoryTest{
    private lateinit var mockWebServer: MockWebServer
    private lateinit var loggingInterceptor: HttpLoggingInterceptor
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var charactersRepository: CharactersRepository
    private lateinit var charactersApiService: CharactersApiService
    @Before
    public fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher =
            object: Dispatcher() {
                override fun dispatch(request: RecordedRequest): MockResponse {
                    return MockResponse()
                        .setResponseCode(200)
                        .setBody(FileReader.readStringFromFile("json/character.json"))
                }
            }
        mockWebServer.start()
        loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        okHttpClient = buildOkhttpClient(loggingInterceptor)
        charactersApiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharactersApiService::class.java)
        charactersRepository = CharactersRepository(charactersApiService)
    }

    @Test
    fun `Happy Case` (){
        runBlocking {
            val response = charactersRepository.getCharacters()
            response.collect {
                Truth.assertThat(response).isNotNull()
            }
        }
    }
    private fun buildOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @After
    public fun tearDown() {
        mockWebServer.shutdown()
    }
}