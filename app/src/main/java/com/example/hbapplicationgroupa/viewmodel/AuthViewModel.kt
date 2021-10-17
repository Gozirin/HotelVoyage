package com.example.hbapplicationgroupa.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.hbapplicationgroupa.model.authmodule.resetpassword.ResetPasswordModel
import com.example.hbapplicationgroupa.model.authmodule.resetpassword.ResetPasswordResponseModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupa.model.authmodule.adduser.AddUserModel
import com.example.hbapplicationgroupa.model.authmodule.adduser.AddUserResponseModel
import com.example.hbapplicationgroupa.model.authmodule.loginuser.LoginUserModel
import com.example.hbapplicationgroupa.model.authmodule.loginuser.LoginUserResponse
import com.example.hbapplicationgroupa.model.authmodule.forgotpassword.ForgotPasswordResponseModel
import com.example.hbapplicationgroupa.model.authmodule.loginuser.LoginUserResponseModel
import com.example.hbapplicationgroupa.repository.authmodulerepository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel() {
    private val _addUserResponse: MutableLiveData<Response<AddUserResponseModel>> = MutableLiveData()
    val addUserResponse: LiveData<Response<AddUserResponseModel>> = _addUserResponse
    var forgotPasswordEmail = MutableLiveData<ForgotPasswordResponseModel>()

    //Login authentication LiveData
    private val _getLoginAuthLiveData: MutableLiveData<LoginUserResponseModel?> = MutableLiveData()
    val getLoginAuthLiveData: LiveData<LoginUserResponseModel?> = _getLoginAuthLiveData

    fun addUser(userInfo : AddUserModel){
        viewModelScope.launch{
            try{
                val response = authRepository.addUser(userInfo)
                _addUserResponse.value = response
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    //Method to make login network call
     fun login(email: String, password: String){
        val loginUserModel = LoginUserModel(email, password)

        viewModelScope.launch {
            try {
               val response = authRepository.loginUser(loginUserModel)
                if (response.isSuccessful){
                    try {
                        _getLoginAuthLiveData.value = response.body()
                    }catch (e: Exception){
                        _getLoginAuthLiveData.postValue(LoginUserResponseModel(LoginUserResponse("",""),false,"Unexpected Error, kindly check your Network",400))
                    }
                } else {
                    _getLoginAuthLiveData.postValue(LoginUserResponseModel(LoginUserResponse("",""),false,"Email is not registered/Account might not be Activated",403))
                }
            }catch (e: Exception){
                _getLoginAuthLiveData.postValue(LoginUserResponseModel(LoginUserResponse("",""),false,"Unexpected Error, kindly check your Network",400))
                e.printStackTrace()
            }

        }
    }


    //ResetPassword authentication LiveData
    private val _resetPasswordLiveData: MutableLiveData<Response<ResetPasswordResponseModel>> = MutableLiveData()
    val resetPasswordLiveData: LiveData<Response<ResetPasswordResponseModel>> = _resetPasswordLiveData

    //Function to make ResetPassword network call
    fun resetUserPassword(token: String,email: String, newPassword: String, confirmPassword: String){
        val resetPasswordModel = ResetPasswordModel(token,email, newPassword, confirmPassword)
        viewModelScope.launch (Dispatchers.IO){
            try {
                val response = authRepository.resetPassword(resetPasswordModel)
                _resetPasswordLiveData.postValue(response)
                Log.d("ResetPassword 2:", response.body().toString())
            }catch (e: Exception){
                Log.d("MQ", "resetPassword: ${e.message}")
            }
        }

    }

    // this is use to make the API call and send the email to the saver
    fun sendForgortPasswordEmail( userEmail : String){
        viewModelScope.launch(Dispatchers.IO) {
            val response : Response<ForgotPasswordResponseModel> = authRepository.forgotPassword( userEmail)
            forgotPasswordEmail.postValue(response.body())
        }
    }
}