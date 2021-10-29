package com.example.hbapplicationgroupa.repository.customermodulerepository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.hbapplicationgroupa.adapter.pastbookings_adapter.PastBookingPagingDataSource
import com.example.hbapplicationgroupa.adapter.pastbookings_adapter.PastBookingPagingDataSource_Factory
import com.example.hbapplicationgroupa.database.dao.WishlistByPageNumberDao
import com.example.hbapplicationgroupa.model.customermodule.addcustomerratingsbyhotelid.RatingsByHotelIdResponseModel
import com.example.hbapplicationgroupa.model.customermodule.addcustomerreviewbyhotelid.ReviewByHotelIdResponseModel
import com.example.hbapplicationgroupa.model.customermodule.getCustomerBooking.GetCustomerBookingResponse
import com.example.hbapplicationgroupa.model.customermodule.getcustomerbookingbyuserid.BookingByUserIdResponseModel
import com.example.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber.WishlistByPageNumberResponseModel
import com.example.hbapplicationgroupa.model.usermodule.updateuserbyid.UpdateUserByIdModel
import com.example.hbapplicationgroupa.model.usermodule.updateuserbyid.UpdateUserByIdResponseModel
import com.example.hbapplicationgroupa.network.CustomerModuleApiInterface
import retrofit2.Response
import javax.inject.Inject

class CustomerRepository @Inject constructor(
    private val customerModuleApiInterface: CustomerModuleApiInterface,
    private val wishlistByPageNumberDao: WishlistByPageNumberDao
    ): CustomerRepositoryInterface {

    override suspend fun getCustomerBookingsByUserId(
//        userId: String,
        pageNumber: Int,
        pageSize: Int,
        authToken: String
    ): Response<GetCustomerBookingResponse> {
        return customerModuleApiInterface.getCustomerBookingsByUserId(pageNumber, pageSize, authToken)
    }

    override suspend fun addCustomerReviewByHotelId(hotelId: String): Response<ReviewByHotelIdResponseModel> {
        return customerModuleApiInterface.addCustomerrReviewByHotelId(hotelId)
    }

    override suspend fun addCustomerRatingsByHotelId(
        rating: Int,
        hotelId: String
    ): Response<RatingsByHotelIdResponseModel> {
        return customerModuleApiInterface.addCustomerRatingsByHotelId(rating, hotelId)
    }

    override suspend fun getCustomerWishListByPageNumber(
        userId: String,
        pageNumber: Int,
        pageSize: Int
    ): Response<WishlistByPageNumberResponseModel> {
        return customerModuleApiInterface.getCustomerWishListByPageNumber(userId, pageNumber, pageSize)
    }

    override suspend fun updateUser(
        authToken: String,
        updateUserModel: UpdateUserByIdModel
    ): Response<UpdateUserByIdResponseModel> {
        return customerModuleApiInterface.updateUser(authToken, updateUserModel)
    }
}