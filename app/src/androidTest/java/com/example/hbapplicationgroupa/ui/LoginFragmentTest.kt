package com.example.hbapplicationgroupa.ui

import androidx.fragment.app.somtesting.FragmentScenario
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
class LoginFragmentTest{

    private lateinit var scenario: FragmentScenario<LoginFragment>

    @Before
    fun setUp(){
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_HBApplicationGroupA)
    }
    @Test
    fun check_if_data_is_inValid(){
        Espresso.onView(withId(R.id.tvEmailTe_login_screen)).perform(ViewActions.typeText("mohammed4@gmail.com"), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.tvEditPassword_login_screen)).perform(ViewActions.typeText("08130228099"), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.btn_login_screen)).perform(ViewActions.click())
    }


}