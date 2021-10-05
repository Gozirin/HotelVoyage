package com.example.hbapplicationgroupa.network.hotelbookingapi

import com.example.hbapplicationgroupa.models.adminModule.usersByPageNumberModel.UsersByPageNumber
import com.example.hbapplicationgroupa.models.customerModule.customerBookingsModel.CustomerBookings
import com.example.hbapplicationgroupa.models.customerModule.customerWishlistModel.CustomerWishList
import com.example.hbapplicationgroupa.models.hotelModule.hotelByIdModel.HotelById
import com.example.hbapplicationgroupa.models.hotelModule.hotelRatingsModel.HotelRatings
import retrofit2.Response
import retrofit2.http.*


interface HBCustomerModuleApi {

    @POST("Customer/create booking/{userId}")
    suspend fun addCustomerBookingByHotelId(@Path("userId") userId: String): Response<List<UsersByPageNumber>>


    @GET("Customer/get-bookings/{userId}")
    suspend fun getCustomerBookingsByUserId(
        @Path("userId") userId: String,
        @Query("pageNumber") pageNumber: Int,
        @Query("pageSize") pageSize: Int
    ): Response<List<CustomerBookings>>


    @POST("Customer/add-review/{hotelId}")
    suspend fun addCustomerRReviewByHotelId(@Path("hotelId") hotelId: String): Response<HotelById>


    @POST("Customer/add-ratings/{hotelId}/{rating}")
    suspend fun addCustomerRatingsByHotelId(
        @Path("rating") rating: Int,
        @Path("hotelId") hotelId: String
    ): Response<HotelRatings>


    @GET("Customer/{userId}/wishlist")
    suspend fun getCustomerWishListByPageNumber(
        @Path("userId") userId: String,
        @Query("pageNumber") pageNumber: Int,
        @Query("pageSize") pageSize: Int
    ): Response<List<CustomerWishList>>


    @PATCH("Customer/update-review/{hotelId}")
    suspend fun updateCustomerReviewByHotelId(@Path("hotelId") hotelId: String): Response<HotelById>
}