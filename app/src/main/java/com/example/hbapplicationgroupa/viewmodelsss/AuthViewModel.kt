package com.example.hbapplicationgroupa.viewmodelsss

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupa.model.authmodule.loginuser.LoginUserModel
import com.example.hbapplicationgroupa.model.authmodule.loginuser.LoginUserResponseModel
import com.example.hbapplicationgroupa.repository.authmodulerepository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel() {
    //Login authentication error message
    var loginErrorMsg: String = ""

    private var response: Response<LoginUserResponseModel>? = null


    //Login authentication LiveData
    private val _getLoginAuthLiveData: MutableLiveData<Response<LoginUserResponseModel>> = MutableLiveData()
    val getLoginAuthLiveData: LiveData<Response<LoginUserResponseModel>> = _getLoginAuthLiveData


    fun login(email: String, password: String) {
        val loginUserModel = LoginUserModel(email, password)
        viewModelScope.launch {
            try{
                response = authRepository.loginUser(loginUserModel)
            }catch (e: Exception){
                loginErrorMsg = "No internet connection"
            }

            if (response != null && response?.isSuccessful!!){
                try {
                    _getLoginAuthLiveData.value = response
                }catch (e: Exception){
                    loginErrorMsg = e.message.toString()
                }
            }else{
                loginErrorMsg = response?.errorBody().toString()
            }


        }
    }

}