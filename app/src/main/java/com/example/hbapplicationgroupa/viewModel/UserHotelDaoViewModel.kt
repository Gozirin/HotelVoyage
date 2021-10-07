package com.example.hbapplicationgroupa.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hbapplicationgroupa.models.hotelModule.userHotelsModel.UserHotelData
import com.example.hbapplicationgroupa.repository.userHotelDaoRepository.UserHotelDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class UserHotelDaoViewModel (val userHotelDaoRepository: UserHotelDaoRepository): ViewModel() {
      val userHotelLiveData: MutableLiveData<List<UserHotelData>> = MutableLiveData()
}