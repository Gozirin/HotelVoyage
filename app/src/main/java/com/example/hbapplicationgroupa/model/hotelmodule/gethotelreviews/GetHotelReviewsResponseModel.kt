package com.example.hbapplicationgroupa.model.hotelmodule.gethotelreviews

data class GetHotelReviewsResponseModel(
    val statusCode: String,
    val success: Boolean,
    val data: ArrayList<GetHotelReviewsResponseItem>,
    val Message: String,
    val errors: String?
)
