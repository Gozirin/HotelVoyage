package com.example.hbapplicationgroupa.model.customermodule.addcustomerreviewbyhotelid

import com.example.hbapplicationgroupa.model.customermodule.addcustomerratingsbyhotelid.HotelIdModel

data class ReviewByHotelIdResponseModel(
    val statusCode: String,
    val success: Boolean,
    val Data: HotelIdModel,
    val Message: String
)
