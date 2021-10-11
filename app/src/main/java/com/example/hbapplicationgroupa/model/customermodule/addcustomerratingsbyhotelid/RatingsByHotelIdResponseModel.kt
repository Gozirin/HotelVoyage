package com.example.hbapplicationgroupa.model.customermodule.addcustomerratingsbyhotelid

import com.example.hbapplicationgroupa.model.authmodule.adduser.UserIdModel

data class RatingsByHotelIdResponseModel(
    val statusCode: String,
    val success: Boolean,
    val Data: HotelIdModel,
    val Message: String
)
