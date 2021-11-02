package com.example.hbapplicationgroupa.model.hotelmodule.gethotelroombyid

data class GetHotelRoomByIdResponseModel(
    val statusCode: String,
    val success: Boolean,
    val Data: GetHotelRoomByIdResponseItem,
    val Message: String,
    val errors: String?
)
