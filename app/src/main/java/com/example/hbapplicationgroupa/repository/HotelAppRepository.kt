package com.example.hbapplicationgroupa.repository

import com.example.hbapplicationgroupa.network.HotelRetrofitServices
import javax.inject.Inject

class HotelAppRepository @Inject constructor(val api: HotelRetrofitServices): RepositoryInterface {
}