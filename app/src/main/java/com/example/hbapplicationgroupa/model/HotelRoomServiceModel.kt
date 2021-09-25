package com.example.hbapplicationgroupa.model

import com.example.hbapplicationgroupa.R

data class HotelRoomServiceModel(
    val roomImage: Int,
    val roomTitle: String
){
    companion object{
        val roomDataList = arrayListOf(
            HotelRoomServiceModel(R.drawable.deluxeroom, "Deluxe Room"),
            HotelRoomServiceModel(R.drawable.heritagedeluxe, "Single Deluxe Room"),
            HotelRoomServiceModel(R.drawable.heritagedeluxetwo, "Studio King")
        )
    }
}