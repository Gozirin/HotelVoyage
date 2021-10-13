package com.example.hbapplicationgroupa.viewmodelsss

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupa.model.authmodule.resetpassword.ResetPasswordModel
import com.example.hbapplicationgroupa.model.authmodule.resetpassword.ResetPasswordResponseModel
import com.example.hbapplicationgroupa.repository.authmodulerepository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel() {


    val resetPasswordLiveData: MutableLiveData<Response<ResetPasswordResponseModel>> = MutableLiveData()


    fun resetUserPassword(email: String, token: String, newPassword: String, confirmPassword: String){
        val resetPasswordModel = ResetPasswordModel(email, token, newPassword, confirmPassword)
        viewModelScope.launch (Dispatchers.IO){
            try {
                val response = authRepository.resetPassword(resetPasswordModel)
                resetPasswordLiveData.postValue(response)
            }catch (e: Exception){
                Log.d("MQ", "resetPassword: ${e.message}")
            }
        }

    }

}