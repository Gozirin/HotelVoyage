package com.example.hbapplicationgroupa.repository

import androidx.lifecycle.LiveData
import com.example.hbapplicationgroupa.models.adminModule.usersByPageNumberModel.UsersByPageNumber
import com.example.hbapplicationgroupa.models.customerModule.customerBookingsModel.CustomerBookings
import com.example.hbapplicationgroupa.models.customerModule.customerBookingsModel.CustomerBookingsData
import com.example.hbapplicationgroupa.models.customerModule.customerWishlistModel.CustomerWishList
import com.example.hbapplicationgroupa.models.hotelModule.hotelByIdModel.HotelById
import com.example.hbapplicationgroupa.models.hotelModule.hotelRatingsModel.HotelRatings
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query

interface CustomerBookingDaoRepositoryInterface {

    //Get all bookings
    fun getCustomerBookings(): LiveData<List<CustomerBookingsData>>

    //Add a new booking
    fun insertNewBooking(customerBookingsData: CustomerBookingsData)

    //Remove a booking
    fun removeABooking(customerBookingsData: CustomerBookingsData)

    suspend fun addCustomerBookingByHotelId(userId: String): Response<List<UsersByPageNumber>>


    suspend fun getCustomerBookingsByUserId(userId: String, pageNumber: Int, pageSize: Int): Response<List<CustomerBookings>>

    suspend fun addCustomerRReviewByHotelId(hotelId: String): Response<HotelById>

    suspend fun addCustomerRatingsByHotelId(rating: Int, hotelId: String): Response<HotelRatings>

    suspend fun getCustomerWishListByPageNumber(userId: String, pageNumber: Int, pageSize: Int): Response<List<CustomerWishList>>

    suspend fun updateCustomerReviewByHotelId(hotelId: String): Response<HotelById>


}