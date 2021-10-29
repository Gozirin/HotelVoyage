package com.example.hbapplicationgroupa.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber.WishlistByPageNumberResponseItems
import com.example.hbapplicationgroupa.model.hotelmodule.allhotels.PageItem
import com.example.hbapplicationgroupa.repository.customermodulerepository.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(private val customerRepository: CustomerRepository): ViewModel() {

    val _wishListLiveData: MutableLiveData<ArrayList<WishlistByPageNumberResponseItems>> = MutableLiveData()


    fun getWishList(){
        viewModelScope.launch(Dispatchers.IO){
            val result = customerRepository.getCustomerWishListByPageNumber()
            if (result.isSuccessful){
                Log.d("getWishListVM", result.body().toString())
                val data = result.body()?.Data.let {
                    _wishListLiveData.postValue(it)
                }
            }

        }
    }
}