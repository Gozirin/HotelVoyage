package com.example.hbapplicationgroupa.models.hotelModule.userTransactionsModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_transactions_table")
data class UserTransactionsData(
    @PrimaryKey(autoGenerate = true)
    val tableNumber: Int,
    val bookingId: String,
    val transactionReference: String,
    val amount: Int,
    val status: String,
    val methodOfPayment: String,
    val createdAt: String,
    val updatedAt:String
)
