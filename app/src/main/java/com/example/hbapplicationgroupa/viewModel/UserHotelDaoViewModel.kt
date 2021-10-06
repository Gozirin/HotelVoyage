package com.example.hbapplicationgroupa.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hbapplicationgroupa.models.hotelModule.userHotelsModel.UserHotelData
import com.example.hbapplicationgroupa.repository.UserHotelDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserHotelDaoViewModel @Inject constructor(val userHotelDaoRepository: UserHotelDaoRepository): ViewModel() {
      val userHotelLiveData: MutableLiveData<List<UserHotelData>> = MutableLiveData()
}