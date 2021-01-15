package com.sammy.androidarchitecture.data

import com.google.common.io.Resources
import com.sammy.androidarchitecture.utils.Constants.CHARACTER
import  okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.io.File
import java.net.HttpURLConnection

open class MockRequestDispatcher: Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            CHARACTER -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("json/characters.json"))
            }
            else -> throw  throw IllegalArgumentException("Unknown Request Path ${request.path.toString()}")
        }
    }

    //get gson
    fun getJson(path:String):String{
        val uri = Resources.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }
}