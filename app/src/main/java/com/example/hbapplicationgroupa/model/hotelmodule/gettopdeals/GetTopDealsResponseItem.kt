package com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "topDeals")
data class GetTopDealsResponseItem(
    @PrimaryKey
    val tableNumber: Int,
    val name: String,
    val thumbnail: String,
    val price: Float
)
