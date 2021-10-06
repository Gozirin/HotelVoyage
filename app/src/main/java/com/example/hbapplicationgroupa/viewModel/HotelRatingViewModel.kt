package com.example.hbapplicationgroupa.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hbapplicationgroupa.models.adminModule.usersByPageNumberModel.UsersByPageNumber
import com.example.hbapplicationgroupa.models.hotelModule.hotelRatingsModel.HotelRatingsData
import com.example.hbapplicationgroupa.repository.HotelRatingDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HotelRatingViewModel @Inject constructor(val hotelRatingDaoRepository: HotelRatingDaoRepository): ViewModel() {
    val  hotelRatingLiveData: MutableLiveData<List<HotelRatingsData>> = MutableLiveData()
    val usersPageLiveData: MutableLiveData<List<UsersByPageNumber>> = MutableLiveData()

}