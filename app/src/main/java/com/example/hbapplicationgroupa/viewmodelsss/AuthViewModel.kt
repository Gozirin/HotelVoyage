package com.example.hbapplicationgroupa.viewmodelsss

import androidx.lifecycle.ViewModel
import com.example.hbapplicationgroupa.repos.authmodulerepository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel() {

}