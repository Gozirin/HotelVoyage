package com.example.hbapplicationgroupa.models.hotelModule.hotelTransactionsModel

import androidx.room.Entity

@Entity(tableName = "hotel_transactions_table")
data class HotelTransactionsData(
    val bookingId: String,
    val transactionReference: String,
    val amount: String,
    val status: String,
    val methodOfPayment: String,
    val createdAt: String,
    val updatedAt: String
)
