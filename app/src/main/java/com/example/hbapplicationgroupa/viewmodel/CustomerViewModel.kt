package com.example.hbapplicationgroupa.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber.WishlistByPageNumberResponseItems
import com.example.hbapplicationgroupa.repository.customermodulerepository.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.example.hbapplicationgroupa.adapter.pastbookings_adapter.PastBookingPagingDataSource
import com.example.hbapplicationgroupa.database.AuthPreference
import com.example.hbapplicationgroupa.model.usermodule.updateuserbyid.UpdateUserByIdModel
import com.example.hbapplicationgroupa.model.usermodule.updateuserbyid.UpdateUserByIdResponseModel
import com.example.hbapplicationgroupa.network.CustomerModuleApiInterface
import com.example.hbapplicationgroupa.model.customermodule.addcustomerratingsbyhotelid.HotelIdRatingsModel
import com.example.hbapplicationgroupa.model.customermodule.addcustomerratingsbyhotelid.RatingsByHotelIdResponseModel
import com.example.hbapplicationgroupa.model.customermodule.addcustomerreviewbyhotelid.HotelIdModel
import com.example.hbapplicationgroupa.model.customermodule.addcustomerreviewbyhotelid.ReviewByHotelIdResponseModel
import com.example.hbapplicationgroupa.model.customermodule.getCustomerBooking.PageItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.security.AccessController.getContext
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(private val customerRepository: CustomerRepository, private val api: CustomerModuleApiInterface): ViewModel() {
    //class CustomerViewModel @Inject constructor(private val customerRepository: CustomerRepository): ViewModel() {
    private val _addReviewResponse: MutableLiveData<ReviewByHotelIdResponseModel> =
        MutableLiveData()
    val addReviewResponse: LiveData<ReviewByHotelIdResponseModel> = _addReviewResponse

    // wishlist livedata
    private val _wishListLiveData: MutableLiveData<ArrayList<WishlistByPageNumberResponseItems>> =
        MutableLiveData()
    val wishListLiveData: LiveData<ArrayList<WishlistByPageNumberResponseItems>> = _wishListLiveData

    fun getWishList(authToken: String, pageSize: Int, pageNumber: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = customerRepository.getCustomerWishListByPageNumber(
                        authToken,
                        pageSize,
                        pageNumber
                    )

                if (response.isSuccessful) {
                    Log.d("getWishListVM", response.body().toString())
                    response.body()?.Data.let {
                        _wishListLiveData.postValue(it)
                    }
                } else {
                    Log.d("getWishListVMerror", response.body()?.errors.toString())
                }
            }catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

    //add customer hotel wishlist
    fun addWishList(token: String,
                    hotelWishlist: WishlistByPageNumberResponseItems,
                    hotelId: String){
        try {
            viewModelScope.launch(Dispatchers.IO){
                val response = customerRepository.addCustomerWishlistById(
                    token,
                    hotelWishlist,
                    hotelId
                )
                if (response.isSuccessful){
                    Toast.makeText(getApplicationContext(),
                        " ${response.body()?.message}",
                        Toast.LENGTH_SHORT)
                        .show()
                }else{
                    Toast.makeText(getApplicationContext(),
                            " ${response.body()?.message}",
                            Toast.LENGTH_SHORT)
                            .show()
                    }
            }
        }catch (e: Exception){
            e.printStackTrace()
            Log.d("VM Error ADD WISHLIST", e.toString())
        }
    }


    //remove customer hotel wishlist
    fun removeWishList(token: String, hotelId: String){
        try {
            viewModelScope.launch(Dispatchers.IO){
                val response = customerRepository.removeCustomerWishlistByHotelId(
                    token, hotelId
                )
                if (response.body()?.succeeded == true){
                    Toast.makeText(getApplicationContext(),
                        " ${response.body()?.message}",
                        Toast.LENGTH_SHORT)
                        .show()
                }else{
                    Toast.makeText(getApplicationContext(),
                        " ${response.body()?.message}",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }catch (e: Exception){
            e.printStackTrace()
            Log.d("VM Error ADD WISHLIST", e.toString())
        }
    }


    private val _addRatingsResponse: MutableLiveData<RatingsByHotelIdResponseModel> =
        MutableLiveData()
    val addRatingsResponse: LiveData<RatingsByHotelIdResponseModel> = _addRatingsResponse


    //    booking history flow data using pagination from PastBookingPagingDataSource
    val bookingHistory: Flow<PagingData<PageItem>> = Pager(PagingConfig(pageSize = 5)) {
        PastBookingPagingDataSource(api)
    }.flow
        .cachedIn(viewModelScope)
//    private val _getUserBookingLiveData: MutableLiveData<GetCustomerBookingResponse> = MutableLiveData()
//    val getUserBookingLiveData : LiveData<GetCustomerBookingResponse> = _getUserBookingLiveData
//
//    fun getUserBooking(pageNumber: Int,
//                       pageSize: Int,
//                       authToken: String){
//        viewModelScope.launch {
//            try {
//                val response = customerRepository.getCustomerBookingsByUserId(pageNumber, pageSize, authToken)
//                if (response.isSuccessful){
//                    _getUserBookingLiveData.value = response.body()
//                    Log.d("PastBooking 1:", response.body().toString())
//                }else{
//                    _getUserBookingLiveData.value = response.body()
//                }
//            }catch (e: Exception){
//                e.printStackTrace()
//            }
//
//        }
//    }


    private val _updateUserLiveData: MutableLiveData<UpdateUserByIdResponseModel> =
        MutableLiveData()
    val updateUserLiveData: LiveData<UpdateUserByIdResponseModel> = _updateUserLiveData

    //method to update user profile
    fun updateUser(
        authToken: String,
        updateUser: UpdateUserByIdModel
    ) {
        viewModelScope.launch {
            try {
                val response = customerRepository.updateUser(authToken, updateUser)
                if (response.isSuccessful) {
                    _updateUserLiveData.value = response.body()
                } else {
                    _updateUserLiveData.value = response.body()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun addReview(comment: String, hotelId: String, token: String) {
        val addReviewModel = HotelIdModel(comment, hotelId)

        viewModelScope.launch {
            try {
                val response =
                    customerRepository.addCustomerReviewByHotelId(addReviewModel, token)
                if (response.isSuccessful) {
                    try {
                        _addReviewResponse.value = response.body()
                    } catch (e: Exception) {
                        _addReviewResponse.postValue(
                            ReviewByHotelIdResponseModel(
                                null,
                                true,
                                "Server Error",
                                500
                            )
                        )
                    }
                } else {
                    _addReviewResponse.postValue(
                        ReviewByHotelIdResponseModel(
                            null,
                            false,
                            "An error occurred, try again later",
                            400
                        )
                    )
                }
            } catch (e: Exception) {
                _addReviewResponse.postValue(
                    ReviewByHotelIdResponseModel(
                        null,
                        false,
                        "Please, check internet connection",
                        500
                    )
                )
            }
        }
    }

    fun addRating(ratings: Int, hotelId: String, token: String) {
        val addRatingsModel = HotelIdRatingsModel(ratings)

        viewModelScope.launch {
            try {
                val response = customerRepository.addCustomerRatingsByHotelId(
                    addRatingsModel,
                    hotelId,
                    token
                )
                if (response.isSuccessful) {
                    try {
                        _addRatingsResponse.value = response.body()
                    } catch (e: Exception) {
                        _addRatingsResponse.postValue(
                            RatingsByHotelIdResponseModel(
                                null,
                                false,
                                "Server error",
                                500
                            )
                        )
                    }
                } else {
                    _addRatingsResponse.postValue(
                        RatingsByHotelIdResponseModel(
                            null,
                            false,
                            "Unauthorized",
                            400
                        )
                    )
                }
            } catch (e: Exception) {
                _addRatingsResponse.postValue(
                    RatingsByHotelIdResponseModel(
                        null,
                        false,
                        "Please, check internet connection",
                        500
                    )
                )
            }
        }
    }
}
