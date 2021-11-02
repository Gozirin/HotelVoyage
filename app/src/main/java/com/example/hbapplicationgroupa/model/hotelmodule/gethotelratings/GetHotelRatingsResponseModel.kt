package com.example.hbapplicationgroupa.model.hotelmodule.gethotelratings

data class GetHotelRatingsResponseModel(
    val statusCode: String,
    val success: Boolean,
    val data: ArrayList<GetHotelRatingsResponseItem>,
    val Message: String,
    val errors: String?
)
