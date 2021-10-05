package com.example.hbapplicationgroupa.models.hotelModule.topDealsModel

import androidx.room.Entity

@Entity(tableName = "top_deals_table")
data class TopDealsData(
    val id: String,
    val name: String,
    val description: String,
    val email: String,
    val address: String,
    val city: String,
    val state: String
)
