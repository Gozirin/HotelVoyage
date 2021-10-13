package com.example.hbapplicationgroupa.viewmodelsss

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseItem
import com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseModel
import com.example.hbapplicationgroupa.repository.hotelmodulerepository.HotelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HotelViewModel @Inject constructor(private val hotelRepository: HotelRepository): ViewModel() {

    private val _topDealsLiveData: MutableLiveData<Response<GetTopDealsResponseModel>> = MutableLiveData()
//    var _topDealsLiveDataResponse: GetTopDealsResponseItem? = null
//    var pageNumber = 1
    val topDealsLiveData : LiveData<Response<GetTopDealsResponseModel>> = _topDealsLiveData

    fun getTopDeals(pageSize: Int, pageNumber: Int){
        viewModelScope.launch {
            val response: Response<GetTopDealsResponseModel> = hotelRepository.getTopDeals(pageSize, pageNumber)
//            try {
                _topDealsLiveData.value = response
//            }catch (e: Exception){
//                _topDealsLiveData.value = null
//            }
        }
    }
}