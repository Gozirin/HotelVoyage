package com.example.hbapplicationgroupa.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseItem
import com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseItem
import com.example.hbapplicationgroupa.repository.hotelmodulerepository.HotelRepository
import com.example.hbapplicationgroupa.utils.Resource
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

    val _topDealsLiveData: MutableLiveData<Resource<GetTopDealsResponseModel>> = MutableLiveData()
    var _topDealsLiveDataResponse: GetTopDealsResponseModel? = null

    var pageNumber = 1

    //store response to mutable variable type and set stored response to an immutable data type that will be made public
    private var _topHotels: MutableLiveData<MutableList<GetTopHotelsResponseItem>> = MutableLiveData()
    val topHotels: LiveData<MutableList<GetTopHotelsResponseItem>> = _topHotels

    private var _topDeals: MutableLiveData<MutableList<GetTopDealsResponseItem>> = MutableLiveData()
    val topDeals: LiveData<MutableList<GetTopDealsResponseItem>> = _topDeals

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



    //the amount of info coming in at a time
    init {
        getTopDealss(10)
    }

    fun getTopDealss(pageSize: Int) = viewModelScope.launch {
        _topDealsLiveData.postValue(Resource.Loading())
        val response = hotelRepository.getTopDealss(pageSize, pageNumber)
        _topDealsLiveData.postValue(handleTopDealssResponse(response))
    }


    private fun handleTopDealssResponse(response: Response<GetTopDealsResponseModel>): Resource<GetTopDealsResponseModel> {
        if (response.isSuccessful){
            response.body()?.let { resultresponse->
                pageNumber++
                if (_topDealsLiveDataResponse == null){
                    _topDealsLiveDataResponse = resultresponse
                }else{
                    val oldDeals = _topDealsLiveDataResponse?.data
                    val newDeals = resultresponse.data
                    oldDeals?.addAll(newDeals)
                }
                return Resource.Success(_topDealsLiveDataResponse ?: resultresponse)
            }
        }
        return Resource.Error(response.message())

    }
}