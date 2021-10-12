package com.example.hbapplicationgroupa.viewmodelsss

import androidx.lifecycle.ViewModel
import com.example.hbapplicationgroupa.repos.customermodulerepository.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(private val customerRepository: CustomerRepository): ViewModel() {

}