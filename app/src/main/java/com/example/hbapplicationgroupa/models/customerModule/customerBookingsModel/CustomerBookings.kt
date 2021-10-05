package com.example.hbapplicationgroupa.models.customerModule.customerBookingsModel

data class CustomerBookings(
    val statusCode: String,
    val success: Boolean,
    val data: ArrayList<CustomerBookingsData>,
    val Message: String,
    val errors: String?
)
