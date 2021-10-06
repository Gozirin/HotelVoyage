package com.example.hbapplicationgroupa.network.hotelbookingapi

import com.example.hbapplicationgroupa.models.hotelModule.hotelAmenitiesModel.HotelAmenities
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HBAmenitiesModuleApi {

    @GET("Hotel/{hotelId}/amenities")
    suspend fun getHotelAmenities(@Path("hotelId") hotelId: String): Response<HotelAmenities>
}