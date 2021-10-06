package com.example.hbapplicationgroupa.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hbapplicationgroupa.models.customerModule.customerBookingsModel.CustomerBookingsData

@Dao
interface CustomerBookingDao {
    //Get all bookings
    @Query("SELECT * FROM customer_bookings_by_pagination_table")
    fun getCustomerBookings(): LiveData<List<CustomerBookingsData>>

    //Add a new booking
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewBooking(customerBookingsData: CustomerBookingsData)

    //Remove a booking
    @Delete
    fun removeABooking(customerBookingsData: CustomerBookingsData)
}