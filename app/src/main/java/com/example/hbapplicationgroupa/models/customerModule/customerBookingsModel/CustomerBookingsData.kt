package com.example.hbapplicationgroupa.models.customerModule.customerBookingsModel

import androidx.room.Entity

//table for customer bookings in pagination gotten by using customer id
@Entity(tableName = "customer_bookings_by_pagination_table")
data class CustomerBookingsData(
    var bookingReference: String,
    var checkIn: String,
    var checkOut: String,
    var numberOfPeople: Int,
    var serviceName: String,
    )
