package com.example.hbapplicationgroupa.viewmodelsss

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupa.model.adaptermodels.Hotel
import com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseItem
import com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseItem
import com.example.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseModel
import com.example.hbapplicationgroupa.repository.hotelmodulerepository.HotelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelViewModel @Inject constructor(private val hotelRepository: HotelRepository): ViewModel() {

    private var _topHotels: MutableLiveData<ArrayList<GetTopHotelsResponseItem>> = MutableLiveData()
    val topHotels: LiveData<ArrayList<GetTopHotelsResponseItem>> = _topHotels

    private var _topDeals: MutableLiveData<ArrayList<GetTopDealsResponseItem>> = MutableLiveData()
    val topDeals: LiveData<ArrayList<GetTopDealsResponseItem>> = _topDeals


    fun getTopHotels() {
        viewModelScope.launch {
            val response = hotelRepository.getTopHotels()
            if (response.isSuccessful) {
                _topHotels.postValue(response.body()?.data)
            }
        }
    }


    fun getTopDeals() {
        viewModelScope.launch {
            val response = hotelRepository.getTopDeals()
            if (response.isSuccessful) {
                _topDeals.postValue(response.body()?.data)
            }
        }
    }


}