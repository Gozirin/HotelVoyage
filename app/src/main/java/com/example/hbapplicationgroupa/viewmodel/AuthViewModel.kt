package com.example.hbapplicationgroupa.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
<<<<<<< HEAD:app/src/main/java/com/example/hbapplicationgroupa/viewmodelsss/AuthViewModel.kt
import com.example.hbapplicationgroupa.model.authmodule.resetpassword.ResetPasswordModel
import com.example.hbapplicationgroupa.model.authmodule.resetpassword.ResetPasswordResponseModel
import androidx.lifecycle.viewModelScope
=======
import com.example.hbapplicationgroupa.model.authmodule.loginuser.LoginUserModel
import com.example.hbapplicationgroupa.model.authmodule.loginuser.LoginUserResponse
>>>>>>> dbe652cb8d6bc6835116cc38b3d15ce220b159ab:app/src/main/java/com/example/hbapplicationgroupa/viewmodel/AuthViewModel.kt
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
    var forgotPasswordEmail = MutableLiveData<ForgotPasswordResponseModel>()

    //Login authentication LiveData
    private val _getLoginAuthLiveData: MutableLiveData<LoginUserResponseModel?> = MutableLiveData()
    val getLoginAuthLiveData: LiveData<LoginUserResponseModel?> = _getLoginAuthLiveData

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
    val resetPasswordLiveData: MutableLiveData<Response<ResetPasswordResponseModel>> = MutableLiveData()

    //Function to make ResetPassword network call
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

    // this is use to make the API call and send the email to the saver
    fun sendForgortPasswordEmail( userEmail : String){
        viewModelScope.launch(Dispatchers.IO) {
            val response : Response<ForgotPasswordResponseModel> = authRepository.forgotPassword( userEmail)
            forgotPasswordEmail.postValue(response.body())
        }
    }
}