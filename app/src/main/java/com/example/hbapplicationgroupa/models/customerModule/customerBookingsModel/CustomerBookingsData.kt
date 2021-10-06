package com.example.hbapplicationgroupa.models.customerModule.customerBookingsModel

import androidx.room.Entity
import androidx.room.PrimaryKey

//table for customer bookings in pagination gotten by using customer id
@Entity(tableName = "customer_bookings_by_pagination_table")
data class CustomerBookingsData(
    @PrimaryKey(autoGenerate = true)
    val tableNumber: Int,
    var bookingReference: String,
    var checkIn: String,
    var checkOut: String,
    var numberOfPeople: Int,
    var serviceName: String,
    )
