package com.sammy.androidarchitecture.ui

import okhttp3.OkHttpClient

object OkHttpProvider {

    val instance: OkHttpClient = OkHttpClient.Builder().build()
}