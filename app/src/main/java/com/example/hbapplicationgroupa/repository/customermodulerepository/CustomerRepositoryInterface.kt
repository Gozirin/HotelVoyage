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
import retrofit2.Response

interface CustomerRepositoryInterface {
//    suspend fun addCustomerBookingByHotelId()

    suspend fun getCustomerBookingsByUserId(
//        userId: String,
        pageNumber: Int,
        pageSize: Int,
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


    //suspend fun updateProfileImage(authToken: String, image: MultipartBody.Part): Response<UpdateProfileImage>

    suspend fun addCustomerWishlistById(
        token: String,
        hotelWishList: PageItem,
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


    //    suspend fun updateCustomerReviewByHotelId(@Path("hotelId") hotelId: String): Response<WorkOnThis>
}