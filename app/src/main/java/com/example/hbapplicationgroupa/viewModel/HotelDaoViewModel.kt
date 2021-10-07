package com.example.hbapplicationgroupa.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hbapplicationgroupa.models.hotelModule.hotelModel.HotelData
import com.example.hbapplicationgroupa.models.hotelModule.hotelRoomsByPriceModel.HotelRoomsByPrice
import com.example.hbapplicationgroupa.models.hotelModule.hotelRoomsVacancyModel.HotelRoomsVacancy
import com.example.hbapplicationgroupa.repository.hotelDaoRepository.HotelDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HotelDaoViewModel @Inject constructor(val hotelDaoRepository: HotelDaoRepository): ViewModel(){
    val allHotelLiveData: MutableLiveData<List<HotelData>> = MutableLiveData()
    val hotelPriceLiveData: MutableLiveData<List<HotelRoomsByPrice>> = MutableLiveData()
    val hotelAvailabilityLiveData: MutableLiveData<List<HotelRoomsVacancy>> = MutableLiveData()
}