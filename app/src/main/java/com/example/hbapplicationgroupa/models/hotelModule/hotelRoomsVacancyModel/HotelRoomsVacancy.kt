package com.example.hbapplicationgroupa.models.hotelModule.hotelRoomsVacancyModel

data class HotelRoomsVacancy(
    val statusCode: String,
    val success: Boolean,
    val data: ArrayList<HotelRoomsVacancyData>,
    val Message: String,
    val errors: String?
)
