package com.example.hbapplicationgroupa.repository.customermodulerepository

import com.example.hbapplicationgroupa.model.customermodule.addcustomerratingsbyhotelid.HotelIdRatingsModel
import com.example.hbapplicationgroupa.model.customermodule.addcustomerratingsbyhotelid.RatingsByHotelIdResponseModel
import com.example.hbapplicationgroupa.model.customermodule.addcustomerreviewbyhotelid.HotelIdModel
import com.example.hbapplicationgroupa.model.customermodule.addcustomerreviewbyhotelid.ReviewByHotelIdResponseModel
import com.example.hbapplicationgroupa.model.customermodule.getCustomerBooking.GetCustomerBookingResponse
import com.example.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber.WishlistByPageNumberResponseModel
import com.example.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber.WishlistResponse
import com.example.hbapplicationgroupa.model.hotelmodule.allhotels.PageItem
import com.example.hbapplicationgroupa.model.usermodule.updateuserbyid.UpdateUserByIdModel
import com.example.hbapplicationgroupa.model.usermodule.updateuserbyid.UpdateUserByIdResponseModel
import com.example.hbapplicationgroupa.model.updatecusomerimage.UpdateProfileImage
import com.example.hbapplicationgroupa.model.usermodule.getuserbyid.GetUserByIdResponseModel
import okhttp3.MultipartBody
import retrofit2.Response

interface CustomerRepositoryInterface {
//    suspend fun addCustomerBookingByHotelId()

    suspend fun getCustomerBookingsByUserId(
//        userId: String,
        pageSize: Int,
        pageNumber: Int,
        authToken: String
    ): Response<GetCustomerBookingResponse>

    suspend fun addCustomerReviewByHotelId(hotelIdModel: HotelIdModel, token:String): Response<ReviewByHotelIdResponseModel>

    suspend fun addCustomerRatingsByHotelId(
        hotelIdRatingsModel: HotelIdRatingsModel,
        hotelId: String,
        token: String
    ): Response<RatingsByHotelIdResponseModel>

    suspend fun getCustomerWishListByPageNumber(
        token: String,
        pageSize: Int,
        pageNumber: Int
    ): Response<WishlistByPageNumberResponseModel>


    suspend fun updateProfileImage(authToken: String, image: MultipartBody.Part): Response<UpdateProfileImage>

    suspend fun addCustomerWishlistById(
        token: String,
        hotelId: String
    ): Response<WishlistResponse>

    suspend fun  removeCustomerWishlistByHotelId(
        token: String,
        hotelId: String
    ): Response<WishlistResponse>

    suspend fun updateUser(
        authToken: String,
        updateUserModel: UpdateUserByIdModel
    ) : Response<UpdateUserByIdResponseModel>

    suspend fun getUserById(token: String): Response<GetUserByIdResponseModel>
    //    suspend fun updateCustomerReviewByHotelId(@Path("hotelId") hotelId: String): Response<WorkOnThis>
}