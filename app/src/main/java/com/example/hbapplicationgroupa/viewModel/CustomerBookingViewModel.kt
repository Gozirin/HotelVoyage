package com.example.hbapplicationgroupa.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hbapplicationgroupa.models.adminModule.usersByPageNumberModel.UsersByPageNumber
import com.example.hbapplicationgroupa.models.customerModule.customerBookingsModel.CustomerBookingsData
import com.example.hbapplicationgroupa.models.hotelModule.hotelByIdModel.HotelById
import com.example.hbapplicationgroupa.repository.customerBookingDaoRepository.CustomerBookingDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class CustomerBookingViewModel(var customerBookingRepository: CustomerBookingDaoRepository): ViewModel() {
    var customerBookingLiveData: MutableLiveData<List<CustomerBookingsData>> = MutableLiveData()
    var customerReviewLiveData: MutableLiveData<List<HotelById>> = MutableLiveData()
    var customerBookingByIdLiveData: MutableLiveData<List<UsersByPageNumber>> = MutableLiveData()
}