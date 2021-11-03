package com.example.hbapplicationgroupa.repository.customermodulerepository

import com.example.hbapplicationgroupa.model.customermodule.addcustomerratingsbyhotelid.HotelIdRatingsModel
import com.example.hbapplicationgroupa.model.customermodule.addcustomerratingsbyhotelid.RatingsByHotelIdResponseModel
import com.example.hbapplicationgroupa.model.customermodule.addcustomerreviewbyhotelid.HotelIdModel
import com.example.hbapplicationgroupa.model.customermodule.addcustomerreviewbyhotelid.ReviewByHotelIdResponseModel
import com.example.hbapplicationgroupa.model.customermodule.getCustomerBooking.GetCustomerBookingResponse
import com.example.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber.WishlistByPageNumberResponseModel
import com.example.hbapplicationgroupa.model.updatecusomerimage.UpdateProfileImage
import com.example.hbapplicationgroupa.model.usermodule.getuserbyid.GetUserByIdResponseModel
import com.example.hbapplicationgroupa.model.usermodule.updateuserbyid.UpdateUserByIdModel
import com.example.hbapplicationgroupa.model.usermodule.updateuserbyid.UpdateUserByIdResponseModel
import com.example.hbapplicationgroupa.network.CustomerModuleApiInterface
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class CustomerRepository @Inject constructor(
    private val customerModuleApiInterface: CustomerModuleApiInterface
    ): CustomerRepositoryInterface {

    override suspend fun getCustomerBookingsByUserId(
//        userId: String,
        pageNumber: Int,
        pageSize: Int,
        authToken: String
    ): Response<GetCustomerBookingResponse> {
        return customerModuleApiInterface.getCustomerBookingsByUserId(pageNumber, pageSize, authToken)
    }

    override suspend fun addCustomerReviewByHotelId(hotelIdModel: HotelIdModel, token:String): Response<ReviewByHotelIdResponseModel> {
        return customerModuleApiInterface.addCustomerrReviewByHotelId(hotelIdModel, token)
    }

    override suspend fun addCustomerRatingsByHotelId(
        hotelIdRatingsModel: HotelIdRatingsModel,
        hotelId: String,
        token:String
    ): Response<RatingsByHotelIdResponseModel> {
        return customerModuleApiInterface.addCustomerRatingsByHotelId(hotelIdRatingsModel, hotelId, token)
    }

    override suspend fun getCustomerWishListByPageNumber(
//        userId: String,
        token: String,
        pageNumber: Int,
        pageSize: Int
    ): Response<WishlistByPageNumberResponseModel> {
        return customerModuleApiInterface.getCustomerWishListByPageNumber(token, pageNumber, pageSize)
    }

    override suspend fun updateUser(
        authToken: String,
        updateUserModel: UpdateUserByIdModel
    ): Response<UpdateUserByIdResponseModel> {
        return customerModuleApiInterface.updateUser(authToken, updateUserModel)
    }

    override suspend fun getUserById(token: String): Response<GetUserByIdResponseModel> {
        return customerModuleApiInterface.getCustomerDetails(token)
    }

    override suspend fun updateProfileImage(authToken: String,image: MultipartBody.Part): Response<UpdateProfileImage> {
       return customerModuleApiInterface.uploadImage(authToken, image)
    }
}