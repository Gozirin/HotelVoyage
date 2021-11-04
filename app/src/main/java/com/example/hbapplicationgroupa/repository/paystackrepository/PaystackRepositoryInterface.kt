package com.example.hbapplicationgroupa.repository.paystackrepository

import com.example.hbapplicationgroupa.model.paystack.InitializeTransaction
import com.example.hbapplicationgroupa.model.paystack.InitializeTransactionResponse
import com.example.hbapplicationgroupa.model.paystack.VerifyTransactionResponse
import retrofit2.Response

interface PaystackRepositoryInterface {

    suspend fun initializeTransactionResponse(payAuthToken: String, paymentInfo: InitializeTransaction): Response<InitializeTransactionResponse>

}