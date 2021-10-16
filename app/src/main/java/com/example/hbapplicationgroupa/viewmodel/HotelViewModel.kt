package com.example.hbapplicationgroupa.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseItem
import com.example.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseItem
import com.example.hbapplicationgroupa.repository.hotelmodulerepository.HotelRepositoryInterface
import com.example.hbapplicationgroupa.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelViewModel @Inject constructor(
    private val hotelRepositoryInterface: HotelRepositoryInterface,
): ViewModel() {
class HotelViewModel @Inject constructor(
    private val hotelRepositoryInterface: HotelRepositoryInterface
    ): ViewModel() {
    //----------------Hotel description----------------
    fun getHotelFromDb() = hotelRepositoryInterface.getHotelDescriptionFromDb()

    //set response from network call to a variable
    private val topHotels = MutableLiveData<Resource<ArrayList<GetTopHotelsResponseItem>>>()
    fun getHotelById(hotelId: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            hotelRepositoryInterface.getHotelDescriptionFromApi(hotelId)
        }catch (e: Exception){
            Log.d("GKB", "getHotelById: ${e.message}")
        }
    }

    //-----------------------------------------------------
    val _topDealsLiveData: MutableLiveData<Resource<GetTopDealsResponseModel>> = MutableLiveData()
    var _topDealsLiveDataResponse: GetTopDealsResponseModel? = null

    private val topDeals = MutableLiveData<Resource<ArrayList<GetTopDealsResponseItem>>>()


    init {
        fetchTopHotels()
        fetchTopDeals()
    }



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
            val response = hotelRepositoryInterface.getTopHotels()
            if (response.isSuccessful) {
                _topHotels.postValue(response.body()?.data)
            }
        }
    }

    private fun fetchTopDeals() {
        viewModelScope.launch {
            val response = hotelRepositoryInterface.getTopDeals()
            if (response.isSuccessful) {
                _topDeals.postValue(response.body()?.data)
            }
        }
    }

    //the amount of info coming in at a time
    init {
        getTopDealss(10)
            topDeals.postValue(Resource.loading(null))
            try {
                    val response = hotelRepositoryInterface.getTopDeals()
                    if (response.isSuccessful) {
                        topDeals.postValue(Resource.success(response.body()?.data) as Resource<ArrayList<GetTopDealsResponseItem>>?)
                    }
            } catch (e: Exception) {
                topHotels.postValue(Resource.error("Network Error", null))
            }
        }
    }

    fun getTopHotels(): LiveData<Resource<ArrayList<GetTopHotelsResponseItem>>> {
        return topHotels
    fun getTopDealss(pageSize: Int) = viewModelScope.launch {
        _topDealsLiveData.postValue(Resource.Loading())
        val response = hotelRepositoryInterface.getTopDealss(pageSize, pageNumber)
        _topDealsLiveData.postValue(handleTopDealssResponse(response))
    }

    fun getTopDeals(): LiveData<Resource<ArrayList<GetTopDealsResponseItem>>> = topDeals


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