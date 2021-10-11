package com.example.hbapplicationgroupa.repos.customermodulerepository

import com.example.hbapplicationgroupa.model.customermodule.addcustomerratingsbyhotelid.RatingsByHotelIdResponseModel
import com.example.hbapplicationgroupa.model.customermodule.addcustomerreviewbyhotelid.ReviewByHotelIdResponseModel
import com.example.hbapplicationgroupa.model.customermodule.getcustomerbookingbyuserid.BookingByUserIdResponseModel
import com.example.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber.WishlistByPageNumberResponseModel
import com.example.hbapplicationgroupa.networksss.CustomerModuleApiInterface
import retrofit2.Response
import javax.inject.Inject

class CustomerRepository @Inject constructor(private val customerModuleApiInterface: CustomerModuleApiInterface): CustomerRepositoryInterface {
    override suspend fun getCustomerBookingsByUserId(
        userId: String,
        pageNumber: Int,
        pageSize: Int
    ): Response<BookingByUserIdResponseModel> {
        return customerModuleApiInterface.getCustomerBookingsByUserId(userId, pageNumber, pageSize)
    }

    override suspend fun addCustomerrReviewByHotelId(hotelId: String): Response<ReviewByHotelIdResponseModel> {
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
}