package com.example.hbapplicationgroupa.model.hotelmodule.gethotelroombyid

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetHotelRoomByIdResponseItem(
    val id: String,
    val roomNo: Int,
    val isBooked: Boolean,
    val roomTypeId: String
): Parcelable
