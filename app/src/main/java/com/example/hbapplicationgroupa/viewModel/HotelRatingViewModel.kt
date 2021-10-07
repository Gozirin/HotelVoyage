package com.example.hbapplicationgroupa.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hbapplicationgroupa.models.adminModule.usersByPageNumberModel.UsersByPageNumber
import com.example.hbapplicationgroupa.models.hotelModule.hotelRatingsModel.HotelRatingsData
import com.example.hbapplicationgroupa.repository.hotelRatingDaoRepository.HotelRatingDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class HotelRatingViewModel (val hotelRatingDaoRepository: HotelRatingDaoRepository): ViewModel() {
    val  hotelRatingLiveData: MutableLiveData<List<HotelRatingsData>> = MutableLiveData()
    val usersPageLiveData: MutableLiveData<List<UsersByPageNumber>> = MutableLiveData()

}