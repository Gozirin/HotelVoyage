package com.example.hbapplicationgroupa.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseItem
import com.example.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseItem
import com.example.hbapplicationgroupa.repository.hotelmodulerepository.HotelRepositoryInterface
import com.example.hbapplicationgroupa.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelViewModel @Inject constructor(
    private val hotelRepositoryInterface: HotelRepositoryInterface,
): ViewModel() {

    //set response from network call to a variable
    private val topHotels = MutableLiveData<Resource<ArrayList<GetTopHotelsResponseItem>>>()

    private val topDeals = MutableLiveData<Resource<ArrayList<GetTopDealsResponseItem>>>()


    init {
        fetchTopHotels()
        fetchTopDeals()
    }

    //set response from network call to variable

    private fun fetchTopHotels() {
        viewModelScope.launch {
            topHotels.postValue(Resource.loading(null))
            try {
                    val response = hotelRepositoryInterface.getTopHotels()
                    if (response.isSuccessful) {
                        topHotels.postValue(Resource.success(response.body()?.data))
                    }
            } catch (e: Exception) {
                topHotels.postValue(Resource.error("Network error", null))
            }
        }
    }

    private fun fetchTopDeals() {
        viewModelScope.launch {
            topDeals.postValue(Resource.loading(null))
            try {
                    val response = hotelRepositoryInterface.getTopDeals()
                    if (response.isSuccessful) {
                        topDeals.postValue(Resource.success(response.body()?.data))
                    }
            } catch (e: Exception) {
                topHotels.postValue(Resource.error("Network Error", null))
            }
        }
    }

    fun getTopHotels(): LiveData<Resource<ArrayList<GetTopHotelsResponseItem>>> {
        return topHotels
    }

    fun getTopDeals(): LiveData<Resource<ArrayList<GetTopDealsResponseItem>>> = topDeals


}