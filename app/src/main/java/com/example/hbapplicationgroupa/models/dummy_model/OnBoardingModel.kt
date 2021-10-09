package com.example.hbapplicationgroupa.models.dummy_model

import androidx.annotation.DrawableRes

data class OnBoardingModel(
    @DrawableRes val imageView: Int,
    var outlineText: String,
    val descriptionText: String
)
