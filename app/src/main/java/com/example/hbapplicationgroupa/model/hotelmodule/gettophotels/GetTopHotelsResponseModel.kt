package com.example.hbapplicationgroupa.model.hotelmodule.gettophotels

data class GetTopHotelsResponseModel(
    val statusCode: String,
    val success: Boolean,
    val data: ArrayList<GetTopHotelsResponseItem>,
    val Message: String,
    val errors: String?
)
