package com.example.hbapplicationgroupa.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseModel
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
}