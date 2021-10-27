package com.example.hbapplicationgroupa.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupa.model.authmodule.resetpassword.ResetPasswordModel
import com.example.hbapplicationgroupa.model.authmodule.resetpassword.ResetPasswordResponseModel
import com.example.hbapplicationgroupa.model.updatecusomerimage.UpdateProfileImage
import com.example.hbapplicationgroupa.repository.customermodulerepository.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(private val customerRepository: CustomerRepository): ViewModel() {
    private val _updateProfileImageLiveData: MutableLiveData<Response<UpdateProfileImage>> = MutableLiveData()
      val updateProfileImageLiveData: MutableLiveData<Response<UpdateProfileImage>> = _updateProfileImageLiveData

  //
    fun makeApiCall(authToken: String, image: MultipartBody.Part){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = customerRepository.updateProfileImage(authToken,image)
                _updateProfileImageLiveData.postValue(response)
            }catch (e:Exception){
                Log.d("MQ", "updateImage: ${e.message}")
            }
        }
    }


}