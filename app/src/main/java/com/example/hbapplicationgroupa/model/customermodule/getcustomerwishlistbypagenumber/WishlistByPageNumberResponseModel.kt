package com.example.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber

data class WishlistByPageNumberResponseModel(
    val statusCode: String,
    val success: Boolean,
    val Data: ArrayList<WishlistByPageNumberResponseItems>,
    val Message: String,
    val errors: String?
)
