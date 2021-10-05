package com.example.hbapplicationgroupa.viewModel

import com.example.hbapplicationgroupa.repository.HotelAppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HotelAppViewModel @Inject constructor(var repository: HotelAppRepository) {
}