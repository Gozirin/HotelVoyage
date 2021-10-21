package com.example.hbapplicationgroupa.model.hotelmodule.getallhotels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "getAllHotel")
data class GetAllHotelsResponseItem(
    @PrimaryKey(autoGenerate = true)
    val tableNumber: Int,
    val address: String,
    val amenities: Any,
    val city: String,
    val description: String,
    val email: String,
    val featuredImage: String,
    val gallery: List<String>,
    val id: String,
    val name: String,
    val phone: String,
    val rating: Double,
    val reviews: Any,
    val roomTypes: List<RoomType>,
    val state: String
)
