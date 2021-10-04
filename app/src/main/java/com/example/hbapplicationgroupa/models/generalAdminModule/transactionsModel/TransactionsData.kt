package com.example.hbapplicationgroupa.models.generalAdminModule.transactionsModel

import androidx.room.Entity
//table for all monthly and yearly transactions
@Entity(tableName = "transaction_table")
data class TransactionsData(
    val companyName: String,
    val businessEmail: String,
    val businessPhone: String,
    val companyAddress: String,
    val state: String,
    val accountName: String,
    val accountNumber: Int

)
