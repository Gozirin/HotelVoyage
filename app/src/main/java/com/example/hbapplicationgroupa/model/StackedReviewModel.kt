package com.example.hbapplicationgroupa.model

import com.example.hbapplicationgroupa.R

/**
 * This is the data class for the stacked review recycler view in Hotel Description Fragment
 */
data class StackedReviewModel(val img : Int) {
    companion object{
        //Fake data list to test recyclerView; This can be deleted
        val imgList = arrayListOf(
            StackedReviewModel(R.drawable.person1),
            StackedReviewModel(R.drawable.person2),
            StackedReviewModel(R.drawable.person3),
            StackedReviewModel(R.drawable.person4)
        )
    }
}