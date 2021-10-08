package com.example.hbapplicationgroupa.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hbapplicationgroupa.models.hotelModule.amenities.AmenitiesData
import com.example.hbapplicationgroupa.models.hotelModule.hotelAmenitiesModel.HotelAmenities
import com.example.hbapplicationgroupa.repository.amenitiesDaoRepository.AmenitiesDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class HotelAmenitiesViewModel
    @Inject constructor(var repository: AmenitiesDaoRepository): ViewModel() {
    var amenitiesDaoLiveData: MutableLiveData<List<AmenitiesData>> = MutableLiveData()
    var adminLiveData: MutableLiveData<List<HotelAmenities>> = MutableLiveData()
}