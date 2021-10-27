package com.example.hbapplicationgroupa.utils

//This interface sends data from rooms bottom sheet to booking details fragment
interface RoomTypeAdapterInterface {
    fun getSelectedRoomTypes(position: Int, name: String)
}