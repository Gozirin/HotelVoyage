package com.example.hbapplicationgroupa.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.hbapplicationgroupa.R
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class ExploreHomeAfterSearchFragmentTest {

    @Before
    fun setUp() {
    }

    @Test
    fun onCreateView() {
    }

    @Test
    fun onViewCreated() {
        Espresso.onView(withId(R.id.view_and_arrow))
            .perform(click())
        //clicking search view
        Espresso.onView(withId(R.id.topHotel_searchView))
            .perform(click())
        //swiping the topHotel recyclerview
        Espresso.onView(withId(R.id.exploreHomeAfterSearchFragmentrecyclerView))
            .perform(swipeLeft()).perform(swipeRight())
        Espresso.onView(withId(R.id.exploreHomeAfterSearchFragmentRecyclerView2))
            .perform(swipeLeft()).perform(swipeRight())

        //swiping the topHotel recyclerview
        Espresso.onView(withId(R.id.exploreHomeAfterSearchFragmentrecyclerView))
            .perform(swipeLeft())

//        Espresso.onView(withId(R.id.exploreHomeAfterSearchFragmentrecyclerView))
//            .perform(typeText("scissors" )).perform(click())
    }
}