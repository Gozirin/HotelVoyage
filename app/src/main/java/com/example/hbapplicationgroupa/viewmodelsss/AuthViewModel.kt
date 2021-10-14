package com.example.hbapplicationgroupa.viewmodelsss

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupa.model.authmodule.loginuser.LoginUserModel
import com.example.hbapplicationgroupa.model.authmodule.loginuser.LoginUserResponse
import com.example.hbapplicationgroupa.model.authmodule.loginuser.LoginUserResponseModel
import com.example.hbapplicationgroupa.repository.authmodulerepository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel() {

    //Login authentication LiveData
    private val _getLoginAuthLiveData: MutableLiveData<LoginUserResponseModel?> = MutableLiveData()
    var getLoginAuthLiveData: LiveData<LoginUserResponseModel?> = _getLoginAuthLiveData

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

}