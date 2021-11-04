package com.example.hbapplicationgroupa.model.paystack

data class InitializeTransactionResponse(
    val `data`: Data,
    val message: String,
    val status: Boolean
)