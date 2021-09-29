package com.example.hbapplicationgroupa.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hbapplicationgroupa.R
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegisterFragmentTest{
    private lateinit var scenario: FragmentScenario<RegisterFragment>

    @Before
    fun setUp(){
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_HBApplicationGroupA)
    }



    @Test
    fun check_if_data_is_inValid(){
        onView(withId(R.id.tv_register_userName)).perform(ViewActions.typeText("Abass Adisa Richard Tope"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.tvEmailText_register)).perform(ViewActions.typeText("mohammedquraysh4@gmail.com"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.tvEditPassword_register)).perform(ViewActions.typeText("6373737373"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.btn_register)).perform(ViewActions.click())
    }

}