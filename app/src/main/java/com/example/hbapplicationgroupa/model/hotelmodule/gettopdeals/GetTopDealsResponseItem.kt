package com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals

import androidx.room.PrimaryKey

data class GetTopDealsResponseItem(
    @PrimaryKey
    val tableNumber: Int,
    val name: String,
    val price: Float
)
