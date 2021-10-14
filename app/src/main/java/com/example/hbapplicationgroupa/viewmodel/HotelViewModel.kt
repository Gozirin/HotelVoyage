package com.example.hbapplicationgroupa.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hbapplicationgroupa.repository.hotelmodulerepository.HotelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HotelViewModel @Inject constructor(private val hotelRepository: HotelRepository): ViewModel() {

}