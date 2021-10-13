package com.example.hbapplicationgroupa.viewmodelsss

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupa.model.authmodule.forgotpassword.ForgotPasswordResponseModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupa.model.authmodule.loginuser.LoginUserModel
import com.example.hbapplicationgroupa.model.authmodule.loginuser.LoginUserResponseModel
import com.example.hbapplicationgroupa.repository.authmodulerepository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel() {
    var forgotPasswordEmail = MutableLiveData<ForgotPasswordResponseModel>()
    //Login authentication error message
    var loginErrorMsg: String = ""


    //Login authentication LiveData
    private val _getLoginAuthLiveData: MutableLiveData<Response<LoginUserResponseModel>> = MutableLiveData()
    val getLoginAuthLiveData: LiveData<Response<LoginUserResponseModel>> = _getLoginAuthLiveData


    //Method to make login network call
    fun login(email: String, password: String) {
        val loginUserModel = LoginUserModel(email, password)
        viewModelScope.launch {
            try{
                val response = authRepository.loginUser(loginUserModel)
                if (response.isSuccessful){
                    _getLoginAuthLiveData.value = response
                    loginErrorMsg = response.body()!!.message
                    Log.d("AuthViewModel 2: ", loginErrorMsg)
                }else{
                    loginErrorMsg = response.body()!!.message
                    Log.d("AuthViewModel 3: ", loginErrorMsg)
                }
            }catch (e: Exception){
                loginErrorMsg = e.message.toString()
                Log.d("AuthViewModel 1: ", loginErrorMsg)
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