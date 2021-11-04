package com.example.hbapplicationgroupa.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupa.model.hotelmodule.bookhotel.BookHotel
import com.example.hbapplicationgroupa.model.paystack.InitializeTransaction
import com.example.hbapplicationgroupa.model.paystack.InitializeTransactionResponse
import com.example.hbapplicationgroupa.model.paystack.VerifyTransactionResponse
import com.example.hbapplicationgroupa.repository.paystackrepository.PaystackRepository
import com.example.hbapplicationgroupa.repository.paystackrepository.PaystackRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PaystackViewModel @Inject constructor
    (private val paystackRepositoryInterface: PaystackRepositoryInterface): ViewModel() {

    private var _initResponse: MutableLiveData<InitializeTransactionResponse> = MutableLiveData()
    val initResponse: LiveData<InitializeTransactionResponse>
        get() = _initResponse

    private var _verifyTransactionResponse: MutableLiveData<VerifyTransactionResponse> = MutableLiveData()
    val verifyTransactionResponse: LiveData<VerifyTransactionResponse>
        get() = _verifyTransactionResponse





     fun getInitResponse(payAuthToken: String, paymentInfo: InitializeTransaction) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = paystackRepositoryInterface.initializeTransactionResponse(payAuthToken, paymentInfo)
                _initResponse.postValue(response.body())
            }catch (e: Exception) {

            }
        }
    }

//    private fun verifyTransaction() {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val response = paystackRepositoryInterface.verifyTransaction()
//                _verifyTransactionResponse.postValue(response.body())
//            }catch (e: Exception) {
//
//            }
//        }
//    }
}