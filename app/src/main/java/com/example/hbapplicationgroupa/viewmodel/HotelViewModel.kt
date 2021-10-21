package com.example.hbapplicationgroupa.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseItem
import com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseItem
import com.example.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseModel
import com.example.hbapplicationgroupa.repository.hotelmodulerepository.HotelRepositoryInterface
import com.example.hbapplicationgroupa.utils.Resource
import com.example.hbapplicationgroupa.utils.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HotelViewModel @Inject constructor(
    private val hotelRepositoryInterface: HotelRepositoryInterface
    ): ViewModel() {
    //----------------Hotel description----------------
    fun getHotelFromDb() = hotelRepositoryInterface.getHotelDescriptionFromDb()

//    fun getRoomTypeFromDb(hotelId: String) = hotelRepositoryInterface.getRoomTypesFromDb(hotelId)

    fun getHotelById(hotelId: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            hotelRepositoryInterface.getHotelDescriptionFromApi(hotelId)
        } catch (e: Exception) {
            Log.d("GKB", "getHotelById: ${e.message}")
        }
    }

    //-----------------------------------------------------
    val _topDealsLiveData: MutableLiveData<Resources<GetTopDealsResponseModel>> = MutableLiveData()
    var _topDealsLiveDataResponse: GetTopDealsResponseModel? = null

    //set response from network call to a variable
    private val topHotels = MutableLiveData<Resource<ArrayList<GetTopHotelsResponseItem>>>()

    var pageNumber = 1

    private var _exploreHomeTopHotels: MutableLiveData<GetTopHotelsResponseModel> = MutableLiveData()
      val  exploreHomeTopHotels: LiveData<GetTopHotelsResponseModel>
      get() = _exploreHomeTopHotels

    private var _exploreHomeTopDeals: MutableLiveData<GetTopDealsResponseModel> = MutableLiveData()
    val exploreHomeTopDeals: LiveData<GetTopDealsResponseModel>
    get() = _exploreHomeTopDeals


//    init {
//        fetchTopHotels()
//        fetchTopDeals()
//    }


    fun fetchTopHotels() {
        viewModelScope.launch {
//            topHotels.postValue(Resource.loading(null))
            try {
                val response = hotelRepositoryInterface.getTopHotels()
                _exploreHomeTopHotels.postValue(response.body())
                Log.d("ExploreHomeVM 6: ", exploreHomeTopHotels.toString())
                Log.d("ExploreHomeVM 5: ", exploreHomeTopDeals.value?.data.toString())
            } catch (e: Exception) {
//                topHotels.postValue(Resource.error("Network error", null))
//                Log.d("ExploreHomeVM 7: ", exploreHomeTopDeals.value?.data.toString())
//                val response = hotelRepositoryInterface.getTopHotels()
//                if (response.isSuccessful) {
//                    exploreHomeTopHotels.postValue(response.body()?.data)
//                }
            }
        }
    }

         fun fetchTopDeals() {
            viewModelScope.launch {
//            topHotels.postValue(Resource.loading(null))
            try {
                val response = hotelRepositoryInterface.getTopDeals()
                if (response.isSuccessful){
                    _exploreHomeTopDeals.value = (response.body())
                    Log.d("ExploreHomeVM 5: ", "${response.body()}")
                }else{
                    Log.d("ExploreHomeVM 5: ", "error")
                }
                Log.d("ExploreHomeVM 4: ", "${response.body()}")
                Log.d("ExploreHomeVM 5: ", exploreHomeTopDeals.value?.data.toString())
            } catch (e: Exception) {
//                topHotels.postValue(Resource.error("Network error", null))
                Log.d("ExploreHomeVM 8: ", exploreHomeTopDeals.value?.data.toString())
//                val response = hotelRepositoryInterface.getTopHotels()
//                if (response.isSuccessful) {
//                    exploreHomeTopDeals.postValue(response.body()?.data)
//                }
            }
        }
        }


        //the amount of info coming in at a time
        init {
            getTopDealss(10)
        }
//           topDeals.postValue(Resource.loading(null))
////            try {
////                val response = hotelRepositoryInterface.getTopDeals()
////                if (response.isSuccessful) {
////                    topDeals.postValue(Resource.success(response.body()?.data) as Resource<ArrayList<GetTopDealsResponseItem>>?)
////                }
////            } catch (e: Exception) {
////                topHotels.postValue(Resource.error("Network Error", null))
////            }

    fun getTopDealss(pageSize: Int) = viewModelScope.launch {
        _topDealsLiveData.postValue(Resources.Loading())
        val response = hotelRepositoryInterface.getTopDealss(pageSize, pageNumber)
        _topDealsLiveData.postValue(handleTopDealssResponse(response))
    }


    private fun handleTopDealssResponse(response: Response<GetTopDealsResponseModel>): Resources<GetTopDealsResponseModel> {
        if (response.isSuccessful) {
            response.body()?.let { resultresponse ->
                pageNumber++
                if (_topDealsLiveDataResponse == null) {
                    _topDealsLiveDataResponse = resultresponse
                } else {
                    val oldDeals = _topDealsLiveDataResponse?.data
                    val newDeals = resultresponse.data
                    oldDeals?.addAll(newDeals)
                }
                return Resources.Success(_topDealsLiveDataResponse ?: resultresponse)
            }
        }
        return Resources.Error(response.message())
    }
//    fun getTopDeals(): LiveData<GetTopDealsResponseModel> = exploreHomeTopDeals
//    fun getTopHotels(): LiveData<GetTopHotelsResponseModel> {
////        Log.d("ExploreHomeVM 3: ", exploreHomeTopDeals.value?.data.toString())
//        return exploreHomeTopHotels
    }



