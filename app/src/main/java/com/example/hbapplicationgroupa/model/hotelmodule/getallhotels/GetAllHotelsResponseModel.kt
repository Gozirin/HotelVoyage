package com.example.hbapplicationgroupa.model.hotelmodule.getallhotels

data class GetAllHotelsResponseModel(
    val statusCode: String,
    val success: Boolean,
    val data: GetAllHotelsResponseItem,
    val Message: String,
    val errors: String?
)
