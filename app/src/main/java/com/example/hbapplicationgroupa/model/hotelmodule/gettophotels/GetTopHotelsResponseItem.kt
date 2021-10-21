package com.example.hbapplicationgroupa.model.hotelmodule.gettophotels

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "topHotels")
data class GetTopHotelsResponseItem(
    @PrimaryKey
    val tableNumber: Int,
    var id: String,
    var name: String,
    val thumbnail: String,
    val price: String,
)
