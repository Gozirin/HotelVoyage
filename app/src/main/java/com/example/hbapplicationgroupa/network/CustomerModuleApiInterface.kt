package com.example.hbapplicationgroupa.network

import com.example.hbapplicationgroupa.model.customermodule.addcustomerratingsbyhotelid.HotelIdRatingsModel
import com.example.hbapplicationgroupa.model.customermodule.addcustomerratingsbyhotelid.RatingsByHotelIdResponseModel
import com.example.hbapplicationgroupa.model.customermodule.addcustomerreviewbyhotelid.HotelIdModel
import com.example.hbapplicationgroupa.model.customermodule.addcustomerreviewbyhotelid.ReviewByHotelIdResponseModel
import com.example.hbapplicationgroupa.model.customermodule.getcustomerbookingbyuserid.BookingByUserIdResponseModel
import com.example.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber.WishlistByPageNumberResponseModel
import com.example.hbapplicationgroupa.model.usermodule.updateuserbyid.UpdateUserByIdModel
import com.example.hbapplicationgroupa.model.usermodule.updateuserbyid.UpdateUserByIdResponseModel
import retrofit2.Response
import retrofit2.http.*

interface CustomerModuleApiInterface {
    //TODO: Need clarity on this API
//    @POST("api/v1/Customer/create-booking")
//    suspend fun addCustomerBookingByHotelId()

    @GET("/api/Customer/bookings")
    suspend fun getCustomerBookingsByUserId(
//        @Path("userId") userId: String,
        @Query("pageSize") pageSize: Int,
        @Query("pageNumber") pageNumber: Int,
        @Header("Authorization") authToken: String
    ): Response<BookingByUserIdResponseModel>

    @POST("api/Review/add-reviews")
    suspend fun addCustomerrReviewByHotelId(
        @Body hotelIdModel: HotelIdModel,
        @Header ("Authorization") token:String
    ): Response<ReviewByHotelIdResponseModel>

    @POST("api/Hotel/{hotelId}/add-ratings")
    suspend fun addCustomerRatingsByHotelId(
        @Body hotelIdRatingsModel: HotelIdRatingsModel,
        @Path("hotelId") hotelId: String,
        @Header ("Authorization") token:String
    ): Response<RatingsByHotelIdResponseModel>

    @GET("api/v1/Customer/{userId}/wishlist/?page={pageNumber}&{pageSize}")
    suspend fun getCustomerWishListByPageNumber(
        @Path("userId") userId: String,
        @Query("pageNumber") pageNumber: Int,
        @Query("pageSize") pageSize: Int
    ): Response<WishlistByPageNumberResponseModel>

    @PUT("api/Customer/update")
    suspend fun updateUser(
        @Header("Authorization") authToken: String,
        @Body updateUserModel: UpdateUserByIdModel
    ) : Response<UpdateUserByIdResponseModel>

//    @PATCH("Customer/update-review/{hotelId}")
//    suspend fun updateCustomerReviewByHotelId(@Path("hotelId") hotelId: String): Response<WorkOnThis>
}