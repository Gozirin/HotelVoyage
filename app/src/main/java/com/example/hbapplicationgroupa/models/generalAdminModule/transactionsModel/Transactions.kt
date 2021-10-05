package com.example.hbapplicationgroupa.models.generalAdminModule.transactionsModel

data class Transactions (
    val statusCode: String,
    val success: Boolean,
    val data: ArrayList<TransactionsData>,
    val Message: String,
    val errors: String?
)