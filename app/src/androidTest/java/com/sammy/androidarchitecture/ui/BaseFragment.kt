package com.sammy.androidarchitecture.ui

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.support.annotation.StyleRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.jakewharton.espresso.OkHttp3IdlingResource
import com.sammy.androidarchitecture.HiltTestActivity
import com.sammy.androidarchitecture.R
import com.sammy.androidarchitecture.ui.characters.CharactersFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.internal.Preconditions
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@UninstallModules(
    NetworkTestModule::class
)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class BaseFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

    protected lateinit var mockWebServer: MockWebServer


    @Before
    open fun setUp() {
        hiltRule.inject()
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = MockServerDispatcher().RequestDispatcher()
        mockWebServer.start(8080)
        OkHttp3IdlingResource.create("okhttp", OkHttpProvider.instance)
    }

    @After
    open fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun givenArticleIsClickedThenNavigateToDetailsScreen() {
        //Launch fragment
        launchNewsListFragment()

        //Click on first article
        onView(withId(R.id.characters_rv)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))

        //Check that it navigates to Detail screen
        Truth.assertThat(navController.currentDestination?.id).isEqualTo(R.id.characterDetailsFragment3)
    }

    private fun launchNewsListFragment() {
        launchFragmentInHiltContainer<CharactersFragment> {
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(R.id.charactersFragment)
            this.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                if (viewLifecycleOwner != null) {
                    // The fragment’s view has just been created
                    Navigation.setViewNavController(this.requireView(), navController)
                }
            }
        }
    }
}

inline fun <reified T : Fragment> launchFragmentInHiltContainer(
    fragmentArgs: Bundle? = null,
    @StyleRes themeResId: Int = R.style.FragmentScenarioEmptyFragmentActivityTheme,
    fragmentFactory: FragmentFactory? = null,
    crossinline action: Fragment.() -> Unit = {}
) {
    val startActivityIntent = Intent.makeMainActivity(
        ComponentName(
            ApplicationProvider.getApplicationContext(),
            HiltTestActivity::class.java
        )
    ).putExtra(FragmentScenario.EmptyFragmentActivity.THEME_EXTRAS_BUNDLE_KEY, themeResId)

    ActivityScenario.launch<HiltTestActivity>(startActivityIntent).onActivity { activity ->
        fragmentFactory?.let {
            activity.supportFragmentManager.fragmentFactory = it
        }
        val fragment: Fragment = activity.supportFragmentManager.fragmentFactory.instantiate(
            Preconditions.checkNotNull(T::class.java.classLoader),
            T::class.java.name
        )

        fragment.arguments = fragmentArgs
        activity.supportFragmentManager
            .beginTransaction()
            .add(android.R.id.content, fragment, "")
            .commitNow()

        fragment.action()
    }
}