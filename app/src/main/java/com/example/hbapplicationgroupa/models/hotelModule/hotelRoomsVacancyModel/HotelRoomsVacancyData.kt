package com.example.hbapplicationgroupa.models.hotelModule.hotelRoomsVacancyModel

import androidx.room.Entity

@Entity(tableName = "hotelRoom_by_vacancy_table")
data class HotelRoomsVacancyData(
    val id: String,
    val isBooked: Boolean,
    val roomType: String
)
