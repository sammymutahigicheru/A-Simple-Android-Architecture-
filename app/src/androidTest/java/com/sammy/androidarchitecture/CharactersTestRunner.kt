package com.sammy.androidarchitecture

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class CharactersTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, className, context)
    }
}