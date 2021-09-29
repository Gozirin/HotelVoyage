package com.example.hbapplicationgroupa.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hbapplicationgroupa.R
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ForgotPasswordFragmentTest{
    private lateinit var scenario: FragmentScenario<ForgotPasswordFragment>
    @Before
    fun setUp(){
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_HBApplicationGroupA)
    }

    @Test
    fun check_if_data_is_responsive(){
        Espresso.onView(withId(R.id.tvEmailText_forgotpassword)).perform(ViewActions.typeText("mohammed4@gmail.com"), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.btn_forgot_password)).perform(ViewActions.click())
    }

}