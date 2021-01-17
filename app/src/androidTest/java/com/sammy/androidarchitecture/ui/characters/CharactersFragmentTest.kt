package com.sammy.androidarchitecture.ui.characters

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.google.common.io.Resources
import com.sammy.androidarchitecture.R
import com.sammy.androidarchitecture.di.NetworkTestModule
import com.sammy.androidarchitecture.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.Rule
import org.junit.Test
import java.io.File
import java.io.FileReader

@UninstallModules(NetworkTestModule::class)
@HiltAndroidTest
class CharactersFragmentTest : TestCase() {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java,true,false)
    private lateinit var mockServer: MockWebServer
    public override fun setUp() {
        super.setUp()
        mockServer = MockWebServer()
        mockServer.start()
    }

    public override fun tearDown() {
        mockServer.shutdown()
    }
    @Test
    fun happyTestCase(){
        mockServer.dispatcher = object: Dispatcher(){
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(getJson("json/character.json"))
            }
        }
        activityTestRule.launchActivity(null)
        Espresso.onView(withId(R.id.characters_rv)).run {
            check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }
    //launch activity
    //get gson
    fun getJson(path:String):String{
        val uri = Resources.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }
}