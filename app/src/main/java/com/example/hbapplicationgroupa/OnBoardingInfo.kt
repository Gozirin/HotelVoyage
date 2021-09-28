package com.example.hbapplicationgroupa

import androidx.annotation.DrawableRes

data class OnBoardingInfo(
    @DrawableRes val imageView: Int,
    var outlineText: String,
    val descriptionText: String
)
