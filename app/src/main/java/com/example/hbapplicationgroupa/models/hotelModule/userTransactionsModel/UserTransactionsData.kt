package com.example.hbapplicationgroupa.models.hotelModule.userTransactionsModel

import androidx.room.Entity

@Entity(tableName = "user_transactions_table")
data class UserTransactionsData(
    val bookingId: String,
    val transactionReference: String,
    val amount: Int,
    val status: String,
    val methodOfPayment: String,
    val createdAt: String,
    val updatedAt:String
)
