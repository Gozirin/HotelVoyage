package com.example.hbapplicationgroupa.model.hotelmodule.gethotelroombyid

data class GetHotelRoomByIdResponseItem(
    val id: String,
    val roomNo: Int,
    val isBooked: Boolean,
    val roomTypeId: String
)
