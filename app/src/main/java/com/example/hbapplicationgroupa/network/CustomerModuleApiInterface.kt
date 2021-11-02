package com.example.hbapplicationgroupa.network

import com.example.hbapplicationgroupa.model.adaptermodels.WishListData
import com.example.hbapplicationgroupa.model.customermodule.addcustomerratingsbyhotelid.HotelIdRatingsModel
import com.example.hbapplicationgroupa.model.customermodule.addcustomerratingsbyhotelid.RatingsByHotelIdResponseModel
import com.example.hbapplicationgroupa.model.customermodule.addcustomerreviewbyhotelid.HotelIdModel
import com.example.hbapplicationgroupa.model.customermodule.addcustomerreviewbyhotelid.ReviewByHotelIdResponseModel
import com.example.hbapplicationgroupa.model.customermodule.getCustomerBooking.GetCustomerBookingResponse
import com.example.hbapplicationgroupa.model.customermodule.getcustomerbookingbyuserid.BookingByUserIdResponseModel
import com.example.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber.WishlistByPageNumberResponseModel
import com.example.hbapplicationgroupa.model.usermodule.updateuserbyid.UpdateUserByIdModel
import com.example.hbapplicationgroupa.model.usermodule.updateuserbyid.UpdateUserByIdResponseModel
import com.example.hbapplicationgroupa.model.updatecusomerimage.UpdateProfileImage
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
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
    ): Response<GetCustomerBookingResponse>

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

    @GET("/api/Customer/wishlist")
    suspend fun getCustomerWishListByPageNumber(
        //@Path("userId") userId: String,
        @Header("Authorization") token: String,
        @Query("pageSize") pageSize: Int,
        @Query("pageNumber") pageNumber: Int
    ): Response<WishlistByPageNumberResponseModel>


    // function to upload to our backend
    @Multipart
    @PATCH("api/Customer/update-image")
    suspend fun uploadImage(
        @Header("Authorization") authToken: String,
        @Part image: MultipartBody.Part
    ): Response<UpdateProfileImage>


    @PUT("api/Customer/update")
    suspend fun updateUser(
        @Header("Authorization") authToken: String,
        @Body updateUserModel: UpdateUserByIdModel
    ) : Response<UpdateUserByIdResponseModel>

//    @PATCH("Customer/update-review/{hotelId}")
//    suspend fun updateCustomerReviewByHotelId(@Path("hotelId") hotelId: String): Response<WorkOnThis>
}