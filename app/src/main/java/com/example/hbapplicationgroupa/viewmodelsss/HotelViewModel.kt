package com.example.hbapplicationgroupa.viewmodelsss

import androidx.lifecycle.ViewModel
import com.example.hbapplicationgroupa.repos.hotelmodulerepository.HotelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HotelViewModel @Inject constructor(private val hotelRepository: HotelRepository): ViewModel() {

}