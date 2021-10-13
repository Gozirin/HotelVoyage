package com.example.hbapplicationgroupa.viewmodelsss

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hbapplicationgroupa.model.authmodule.adduser.AddUserModel
import com.example.hbapplicationgroupa.model.authmodule.adduser.AddUserResponseModel
import com.example.hbapplicationgroupa.repository.authmodulerepository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel() {
    private val _addUserResponse: MutableLiveData<Response<AddUserResponseModel>> = MutableLiveData()
    val addUserResponse: LiveData<Response<AddUserResponseModel>> = _addUserResponse

    fun addUser(userInfo : AddUserModel){
        viewModelScope.launch{
            val response = authRepository.addUser(userInfo)
            _addUserResponse.value = response

        }
    }
}