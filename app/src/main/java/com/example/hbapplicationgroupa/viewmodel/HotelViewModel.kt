package com.example.hbapplicationgroupa.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.hbapplicationgroupa.model.authmodule.resetpassword.ResetPasswordResponseModel
import com.example.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber.WishlistByPageNumberResponseItems
import com.example.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber.WishlistByPageNumberResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.allhotels.Data
import com.example.hbapplicationgroupa.model.hotelmodule.allhotels.PageItem
import com.example.hbapplicationgroupa.model.hotelmodule.bookhotel.BookHotel
import com.example.hbapplicationgroupa.model.hotelmodule.bookhotel.BookHotelResponse
import com.example.hbapplicationgroupa.model.hotelmodule.bookhotel.VerifyBooking
import com.example.hbapplicationgroupa.model.hotelmodule.filterallhotelbylocation.FilterAllHotelByLocation
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelreviews.GetHotelReviewsResponseItem
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelreviews.GetHotelReviewsResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelroombyid.GetHotelRoomByIdResponseModel

import com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseItem
import com.example.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseModel
import com.example.hbapplicationgroupa.repository.customermodulerepository.CustomerRepository
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
    private val hotelRepositoryInterface: HotelRepositoryInterface,
    private val customerRepository: CustomerRepository
    ): ViewModel() {

    var error: String? =  "No Hotel in this location"
    var allHotels: MutableList<PageItem> = mutableListOf()

    /**hotel Review livedata*/
    private var _hotelReview :MutableLiveData<GetHotelReviewsResponseModel> = MutableLiveData()
    val hotelReview: LiveData<GetHotelReviewsResponseModel> = _hotelReview


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







//    val _topDealsLiveData: MutableLiveData<Resource<GetTopDealsResponseModel>> = MutableLiveData()

    val _topDealsLiveData: MutableLiveData<Resources<GetTopDealsResponseModel>> = MutableLiveData()

    var _topDealsLiveDataResponse: GetTopDealsResponseModel? = null


    //------------------All Hotels------------------------------------
    private var _allHotelsLiveData: MutableLiveData<Data> = MutableLiveData()
    var allHotelsLiveData: LiveData<Data> = _allHotelsLiveData

    //------------------Top Hotels ----------------------------------
    private var _topHotelsLiveData: MutableLiveData<ArrayList<GetTopHotelsResponseItem>> = MutableLiveData()
    var topHotelsLiveData: LiveData<ArrayList<GetTopHotelsResponseItem>> = _topHotelsLiveData


    var pageNumber = 1

    private var _exploreHomeTopHotels: MutableLiveData<GetTopHotelsResponseModel> =
        MutableLiveData()
    val exploreHomeTopHotels: LiveData<GetTopHotelsResponseModel>
        get() = _exploreHomeTopHotels

    private var _exploreHomeTopDeals: MutableLiveData<GetTopDealsResponseModel> = MutableLiveData()
    val exploreHomeTopDeals: LiveData<GetTopDealsResponseModel>
        get() = _exploreHomeTopDeals

    private var _bookingInfo: MutableLiveData<BookHotel> = MutableLiveData()
    val bookingInfo: LiveData<BookHotel>
        get() = _bookingInfo

    private var _paymentOption: MutableLiveData<BookHotel> = MutableLiveData()
    val paymentOption: LiveData<BookHotel>
        get() = _paymentOption

    private var _hotelRooms: MutableLiveData<GetHotelRoomByIdResponseModel> = MutableLiveData()
    val hotelRooms: LiveData<GetHotelRoomByIdResponseModel>
        get() = _hotelRooms

    private var _bookingVerificationDetails: MutableLiveData<VerifyBooking> = MutableLiveData()
    val bookingVerificationDetails: LiveData<VerifyBooking>
        get() = _bookingVerificationDetails

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
//                Log.d("ExploreHomeVM 6: ", exploreHomeTopHotels.toString())
//                Log.d("ExploreHomeVM 5: ", "The hell $response")
                println("The response $response")
            } catch (e: Exception) {

                println("the exception is $e")
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
                if (response.isSuccessful) {
                    _exploreHomeTopDeals.value = (response.body())
//                    Log.d("ExploreHomeVM 5: ", "${response.body()}")
                } else {
//                    Log.d("ExploreHomeVM 5: ", "error")
                }
//                Log.d("ExploreHomeVM 4: ", "${response.body()}")
//                Log.d("ExploreHomeVM 5: ", exploreHomeTopDeals.value?.data.toString())
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
//        init {
//            getTopDealss(10)
//        }
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

//    private fun handleAllHotelssResponse(response: Response<GetAllHotelsResponseModel>): Resources<GetAllHotelsResponseModel> {
//        if (response.isSuccessful) {
//            response.body()?.let {
//                pageNumber++
//                if (_allHotelsLiveDataResponse == null) {
//                    _allHotelsLiveDataResponse = it
//                } else {
//                    val oldHotels = _topDealsLiveDataResponse?.data
//                    val newHotels = it.data
//                    oldHotels?.addAll(newHotels)
//                }
//                return Resources.Success(_allHotelsLiveDataResponse ?: it)
//            }
//        }
//        return Resources.Error(response.message())
//    }


    //fetching all hotels from repository interface
    fun fetchAllHotels(){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = hotelRepositoryInterface.getAllHotels()
                if (response.isSuccessful) {
                    val neededData = response.body()?.data?.pageItems as MutableList
                    allHotels = neededData
                    val data = response.body()?.data
                    _allHotelsLiveData.postValue(data!!)
//                    Log.d("VM hotel Repo Interface", data.toString())
                }else{
//                    Log.d("VmError", "No data from api")
                }
            }catch (e:Exception){
                Log.d("VMError", e.message.toString())
            }
        }
    }

    //fetching top hotels from repository interface
    fun fetchTopScreenHotels(){
        viewModelScope.launch(Dispatchers.IO){
            val response = hotelRepositoryInterface.getTopHotels()
            try {
                if (response.isSuccessful) {
                    val topHotels = response.body()?.data
                    _topHotelsLiveData.postValue(topHotels!!)
//                    Log.d("VM hotel Repo Interface", data.toString())
                }else{
//                    Log.d("VmError", "No data from api")
                }
            }catch (e:Exception){
                Log.d("VMError", e.message.toString())
            }
        }
    }


    val filterAllHotelByLocationLiveData: MutableLiveData<FilterAllHotelByLocation> = MutableLiveData()
    val _filterAllHotelByLocationLiveData: LiveData<FilterAllHotelByLocation> =  filterAllHotelByLocationLiveData

    fun filterAllHotelWithLocation(location: String, pageSize: Int, pageNumber: Int){
        viewModelScope.launch(Dispatchers.IO){
               try {
                   val response = hotelRepositoryInterface.filterAllHotelByLocation(location, pageSize, pageNumber)
                   filterAllHotelByLocationLiveData.postValue(response.body())
               }catch (e: Exception){
                   Log.d("MQ", ": ${e.message}")
               }

        }

    }
    fun search(location: String): MutableList<PageItem>{
        val newHotelList = allHotels.filter {

            it.state == location
        }
     return newHotelList as MutableList
    }

    fun getHotelReview2(hotelId:String, token:String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = hotelRepositoryInterface.getHotelReview2(hotelId, token)
                _hotelReview.postValue(response.body())
            } catch (e: Exception) {
                Log.d("Review", "${e.message}")
            }
        }
    }

    fun pushBookHotel(authToken: String, bookHotelInfo: BookHotel) {
        viewModelScope.launch {
            try {
                val response = hotelRepositoryInterface.pushBookHotel(authToken, bookHotelInfo)
                _bookingInfo.postValue(response.body())
            }catch (e: Exception) {
                //handle error
            }
        }
    }

    fun getHotelRoomIdByRoomTypeId(hotelId: String, roomTypeId: String) {
        viewModelScope.launch {
            try {
                val response = hotelRepositoryInterface.getHotelRoomIdByRoomType(hotelId, roomTypeId)
                _hotelRooms.postValue(response.body())
            }catch (e: Exception) {

            }
        }
    }

    fun pushPaymentTransactionDetails(verificationDetails: VerifyBooking) {
        viewModelScope.launch {
            try {
                val response = hotelRepositoryInterface.pushPaymentTransactionDetails(verificationDetails)
                _bookingVerificationDetails.postValue(response.body())
            }catch (e: Exception) {

            }
        }
    }
}