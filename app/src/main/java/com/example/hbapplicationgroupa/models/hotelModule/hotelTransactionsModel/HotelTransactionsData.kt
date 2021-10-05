package com.example.hbapplicationgroupa.models.hotelModule.hotelTransactionsModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hotel_transactions_table")
data class HotelTransactionsData(
    @PrimaryKey(autoGenerate = true)
    val tableNumber: Int,
    val bookingId: String,
    val transactionReference: String,
    val amount: String,
    val status: String,
    val methodOfPayment: String,
    val createdAt: String,
    val updatedAt: String
)
