package com.example.hbapplicationgroupa.model.hotelmodule.getallhotels

import androidx.room.Entity
import androidx.room.PrimaryKey

data class GetAllHotelsResponseItem(
    val id: String,
    val name: String,
    val Rating: Double,
    val cheapestRoomPrice: String


)
