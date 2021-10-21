package com.example.hbapplicationgroupa.utils

import com.example.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseItemRoomTypes

interface BookingDetailsOnclickInterface {
    fun passRoomTypes(roomTypes: ArrayList<GetHotelByIdResponseItemRoomTypes>)
}