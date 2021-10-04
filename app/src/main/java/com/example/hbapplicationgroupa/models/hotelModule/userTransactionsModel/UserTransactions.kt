package com.example.hbapplicationgroupa.models.hotelModule.userTransactionsModel

data class UserTransactions(
    val statusCode: String,
    val success: Boolean,
    val data: ArrayList<UserTransactionsData>,
    val Message: String,
    val errors: String?
)
