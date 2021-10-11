package com.example.hbapplicationgroupa.repos.customermodulerepository

import com.example.hbapplicationgroupa.model.customermodule.addcustomerratingsbyhotelid.RatingsByHotelIdResponseModel
import com.example.hbapplicationgroupa.model.customermodule.addcustomerreviewbyhotelid.ReviewByHotelIdResponseModel
import com.example.hbapplicationgroupa.model.customermodule.getcustomerbookingbyuserid.BookingByUserIdResponseModel
import com.example.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber.WishlistByPageNumberResponseModel
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query

interface CustomerRepositoryInterface {
//    suspend fun addCustomerBookingByHotelId()

    suspend fun getCustomerBookingsByUserId(
        @Path("userId") userId: String,
        @Query("pageNumber") pageNumber: Int,
        @Query("pageSize") pageSize: Int
    ): Response<BookingByUserIdResponseModel>

    suspend fun addCustomerrReviewByHotelId(
        @Path("hotelId")
        hotelId: String
    ): Response<ReviewByHotelIdResponseModel>

    suspend fun addCustomerRatingsByHotelId(
        @Path("rating") rating: Int,
        @Path("hotelId") hotelId: String
    ): Response<RatingsByHotelIdResponseModel>

    suspend fun getCustomerWishListByPageNumber(
        @Path("userId") userId: String,
        @Query("pageNumber") pageNumber: Int,
        @Query("pageSize") pageSize: Int
    ): Response<WishlistByPageNumberResponseModel>

    //    suspend fun updateCustomerReviewByHotelId(@Path("hotelId") hotelId: String): Response<WorkOnThis>
}