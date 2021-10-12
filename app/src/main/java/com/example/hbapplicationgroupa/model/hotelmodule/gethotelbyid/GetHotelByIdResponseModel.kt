package com.example.hbapplicationgroupa.model.hotelmodule.gethotelbyid

data class GetHotelByIdResponseModel(
    val statusCode: String,
    val success: Boolean,
    val itemData: GetHotelByIdResponseItemData,
    val Message: String,
    val errors: String?
)
