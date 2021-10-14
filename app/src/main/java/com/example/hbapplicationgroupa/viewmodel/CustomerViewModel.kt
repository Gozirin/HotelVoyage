package com.example.hbapplicationgroupa.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hbapplicationgroupa.repository.customermodulerepository.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(private val customerRepository: CustomerRepository): ViewModel() {

}