package com.example.hbapplicationgroupa.model.hotelmodule.getallhotels

import androidx.room.Entity

@Entity(tableName = "getAllHotel")
data class GetAllHotelsResponseItem(
    val id: String,
    val name: String,
    val thumbnail: String,
    val Rating: Double,
    val CheapestRoomPrice: String,
    val city: String,
    val state: String,

)
