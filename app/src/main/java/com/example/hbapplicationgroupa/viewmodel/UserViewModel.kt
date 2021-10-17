package com.example.hbapplicationgroupa.viewModel

import androidx.lifecycle.ViewModel
import com.example.hbapplicationgroupa.repository.usermodulerepository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {

}