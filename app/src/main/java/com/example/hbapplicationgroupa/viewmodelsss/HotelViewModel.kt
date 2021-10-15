package com.example.hbapplicationgroupa.viewmodelsss

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseItem
import com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseModel
import com.example.hbapplicationgroupa.repository.hotelmodulerepository.HotelRepository
import com.example.hbapplicationgroupa.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HotelViewModel @Inject constructor(private val hotelRepository: HotelRepository): ViewModel() {

//    private val _topDealsLiveData: MutableLiveData<Response<GetTopDealsResponseModel>> = MutableLiveData()
//    var topDealsLiveData: LiveData<Response<GetTopDealsResponseModel>> = _topDealsLiveData
//
//
//    fun getTopDeals(pageSize:Int, pageNumber:Int){
//
//        viewModelScope.launch {
//            val response = hotelRepository.getTopDeals(pageSize, pageNumber)
//            _topDealsLiveData.value = response
//        }
//    }


    val _topDealsLiveData: MutableLiveData<Resource<GetTopDealsResponseModel>> = MutableLiveData()
    var _topDealsLiveDataResponse: GetTopDealsResponseModel? = null

//    val topDealsLiveData : LiveData<Response<GetTopDealsResponseModel>> = _topDealsLiveData
    var pageNumber = 1

    //the amount of info coming in at a time
    init {
        getTopDeals(10)
    }

    fun getTopDeals(pageSize: Int) = viewModelScope.launch {
        _topDealsLiveData.postValue(Resource.Loading())
        val response = hotelRepository.getTopDeals(pageSize, pageNumber)
        _topDealsLiveData.postValue(handleTopDealsResponse(response))
    }


    private fun handleTopDealsResponse(response: Response<GetTopDealsResponseModel>): Resource<GetTopDealsResponseModel> {
        if (response.isSuccessful){
            response.body()?.let { resultresponse->
                pageNumber++
                if (_topDealsLiveDataResponse == null){
                    _topDealsLiveDataResponse = resultresponse
                }else{
                    val oldHotels = _topDealsLiveDataResponse?.data
                    val newHotels = resultresponse.data
                    oldHotels?.addAll(newHotels)
                }
                return Resource.Success(_topDealsLiveDataResponse ?: resultresponse)
            }
        }
        return Resource.Error(response.message())

    }
}