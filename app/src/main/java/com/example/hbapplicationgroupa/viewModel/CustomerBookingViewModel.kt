package com.example.hbapplicationgroupa.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hbapplicationgroupa.models.customerModule.customerBookingsModel.CustomerBookingsData
import com.example.hbapplicationgroupa.models.hotelModule.hotelByIdModel.HotelById
import com.example.hbapplicationgroupa.repository.CustomerBookingDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CustomerBookingViewModel @Inject constructor(var customerBookingRepository: CustomerBookingDaoRepository): ViewModel() {

    var customerBookingLiveData: MutableLiveData<List<CustomerBookingsData>> = MutableLiveData()
    var customerReviewLiveData: MutableLiveData<List<HotelById>> = MutableLiveData()
}