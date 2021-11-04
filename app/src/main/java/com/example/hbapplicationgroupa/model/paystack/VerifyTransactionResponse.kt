package com.example.hbapplicationgroupa.model.paystack

data class VerifyTransactionResponse(
    val `data`: DataX,
    val message: String,
    val status: Boolean
)