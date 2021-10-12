package com.example.hbapplicationgroupa.viewmodelsss

import androidx.lifecycle.ViewModel
import com.example.hbapplicationgroupa.repos.usermodulerepository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {

}