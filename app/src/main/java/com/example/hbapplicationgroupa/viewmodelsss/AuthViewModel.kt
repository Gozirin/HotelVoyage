package com.example.hbapplicationgroupa.viewmodelsss

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupa.model.authmodule.adduser.AddUserModel
import com.example.hbapplicationgroupa.model.authmodule.adduser.AddUserResponseModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupa.model.authmodule.loginuser.LoginUserModel
import com.example.hbapplicationgroupa.model.authmodule.loginuser.LoginUserResponseModel
import com.example.hbapplicationgroupa.repository.authmodulerepository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel() {
    private val _addUserResponse: MutableLiveData<Response<AddUserResponseModel>> = MutableLiveData()
    val addUserResponse: LiveData<Response<AddUserResponseModel>> = _addUserResponse
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

    fun addUser(userInfo : AddUserModel){
        viewModelScope.launch{
            val response = authRepository.addUser(userInfo)
            _addUserResponse.value = response
        }
    }
}