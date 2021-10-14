package com.example.hbapplicationgroupa.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseItem
import com.example.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseItem
import com.example.hbapplicationgroupa.repository.hotelmodulerepository.HotelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HotelViewModel @Inject constructor(private val hotelRepository: HotelRepository): ViewModel() {
    private val _getHotelByIdLivedata: MutableLiveData<Response<GetHotelByIdResponseModel>> = MutableLiveData()
    val getHotelByIdLivedata: LiveData<Response<GetHotelByIdResponseModel>> get() = _getHotelByIdLivedata

    fun getHotelById(hotelId: String){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = hotelRepository.getHotelById(hotelId)
                _getHotelByIdLivedata.postValue(response)
            }catch (e: Exception){
                Log.d("GKB", "getHotelById: ${e.message}")
            }
        }
    }

    //store response to mutable variable type and set stored response to an immutable data type that will be made public
    private var _topHotels: MutableLiveData<ArrayList<GetTopHotelsResponseItem>> = MutableLiveData()
    val topHotels: LiveData<ArrayList<GetTopHotelsResponseItem>> = _topHotels

    private var _topDeals: MutableLiveData<ArrayList<GetTopDealsResponseItem>> = MutableLiveData()
    val topDeals: LiveData<ArrayList<GetTopDealsResponseItem>> = _topDeals

    //set response from network call to variable

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