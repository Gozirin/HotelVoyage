package com.example.hbapplicationgroupa.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.hbapplicationgroupa.adapter.pastbookings_adapter.PastBookingPagingDataSource
import com.example.hbapplicationgroupa.model.customermodule.getCustomerBooking.GetCustomerBookingResponse
import com.example.hbapplicationgroupa.model.customermodule.getCustomerBooking.PageItem
import com.example.hbapplicationgroupa.model.customermodule.getcustomerbookingbyuserid.BookingByUserIdResponseItems
import com.example.hbapplicationgroupa.model.usermodule.updateuserbyid.UpdateUserByIdModel
import com.example.hbapplicationgroupa.model.usermodule.updateuserbyid.UpdateUserByIdResponseModel
import com.example.hbapplicationgroupa.network.CustomerModuleApiInterface
import com.example.hbapplicationgroupa.repository.customermodulerepository.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(private val customerRepository: CustomerRepository, private val api: CustomerModuleApiInterface): ViewModel() {

//    booking history flow data using pagination from PastBookingPagingDataSource
    val bookingHistory: Flow<PagingData<PageItem>> = Pager(PagingConfig(pageSize = 5)) {
        PastBookingPagingDataSource(api)
    }.flow
        .cachedIn(viewModelScope)
//    private val _getUserBookingLiveData: MutableLiveData<GetCustomerBookingResponse> = MutableLiveData()
//    val getUserBookingLiveData : LiveData<GetCustomerBookingResponse> = _getUserBookingLiveData
//
//    fun getUserBooking(pageNumber: Int,
//                       pageSize: Int,
//                       authToken: String){
//        viewModelScope.launch {
//            try {
//                val response = customerRepository.getCustomerBookingsByUserId(pageNumber, pageSize, authToken)
//                if (response.isSuccessful){
//                    _getUserBookingLiveData.value = response.body()
//                    Log.d("PastBooking 1:", response.body().toString())
//                }else{
//                    _getUserBookingLiveData.value = response.body()
//                }
//            }catch (e: Exception){
//                e.printStackTrace()
//            }
//
//        }
//    }


    private val _updateUserLiveData: MutableLiveData<UpdateUserByIdResponseModel> = MutableLiveData()
    val updateUserLiveData: LiveData<UpdateUserByIdResponseModel> = _updateUserLiveData
    //method to update user profile
    fun updateUser (
        authToken: String,
        updateUser: UpdateUserByIdModel
    ) {
        viewModelScope.launch {
            try {
                val response = customerRepository.updateUser(authToken, updateUser)
                if (response.isSuccessful){
                    _updateUserLiveData.value = response.body()
                }else{
                    _updateUserLiveData.value = response.body()
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}