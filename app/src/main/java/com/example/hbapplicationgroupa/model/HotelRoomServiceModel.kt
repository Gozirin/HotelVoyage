package com.example.hbapplicationgroupa.model

import com.example.hbapplicationgroupa.R

/**
 * Dataclass for HotelRoomService RecyclerView for the HotelDescription Fragment
 */
data class HotelRoomServiceModel(
    val roomImage: Int,
    val roomTitle: String
){
    companion object{
        //Fake data list populated for UI sake
        val roomDataList = arrayListOf(
            HotelRoomServiceModel(R.drawable.deluxeroom, "Deluxe Room"),
            HotelRoomServiceModel(R.drawable.heritagedeluxe, "Single Deluxe Room"),
            HotelRoomServiceModel(R.drawable.heritagedeluxetwo, "Studio King")
        )
    }
}