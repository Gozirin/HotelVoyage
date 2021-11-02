package com.example.hbapplicationgroupa.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.hbapplicationgroupa.R
import junit.framework.TestCase
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProfileFragmentTest{
    private lateinit var scenario:FragmentScenario<ProfileFragment>

    @Before
    fun setup(){
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_HBApplicationGroupA)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun profile_fragment_test_and_the_clicks(){

        onView(withId(R.id.fragment_profile_page)).check(matches(isDisplayed()))
        onView(withId(R.id.fragment_profile_bookings_con)).perform(click())
        onView(withId(R.id.fragment_profile_help_con)).perform(click())
        onView(withId(R.id.fragment_profile_privacy_con)).perform(click())
        onView(withId(R.id.fragment_profile_logout_tv)).perform(click())
    }
}