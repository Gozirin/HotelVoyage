package com.example.hbapplicationgroupa.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupa.model.customermodule.addcustomerreviewbyhotelid.HotelIdModel
import com.example.hbapplicationgroupa.model.customermodule.addcustomerreviewbyhotelid.ReviewByHotelIdResponseModel
import com.example.hbapplicationgroupa.repository.customermodulerepository.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(private val customerRepository: CustomerRepository): ViewModel() {
    private val _addReviewResponse: MutableLiveData<ReviewByHotelIdResponseModel> = MutableLiveData()
    val addReviewResponse: LiveData<ReviewByHotelIdResponseModel> = _addReviewResponse


    fun addReview(comment:String, hotelId:String){
        val addReviewModel = HotelIdModel(comment, hotelId)

        viewModelScope.launch {
            try {
                val response = customerRepository.addCustomerReviewByHotelId(addReviewModel)
                if (response.isSuccessful){
                    try {
                        _addReviewResponse.value = response.body()
                    }catch (e:Exception){
                        _addReviewResponse.postValue(ReviewByHotelIdResponseModel(null, true, "Serve Error", 500))
                    }
                }else{
                    _addReviewResponse.postValue(ReviewByHotelIdResponseModel(null, false, "An error occurred", 400))
                }
            }catch (e:Exception){
                _addReviewResponse.postValue(ReviewByHotelIdResponseModel(null, false, "Please, check internet connection", 500))
            }
        }
    }
}