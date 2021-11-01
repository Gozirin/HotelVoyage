package com.example.hbapplicationgroupa.repository.customermodulerepository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.hbapplicationgroupa.adapter.pastbookings_adapter.PastBookingPagingDataSource
import com.example.hbapplicationgroupa.database.dao.WishlistByPageNumberDao
import com.example.hbapplicationgroupa.model.adaptermodels.WishListData
import com.example.hbapplicationgroupa.model.customermodule.addcustomerratingsbyhotelid.HotelIdRatingsModel
import com.example.hbapplicationgroupa.model.customermodule.addcustomerratingsbyhotelid.RatingsByHotelIdResponseModel
import com.example.hbapplicationgroupa.model.customermodule.addcustomerreviewbyhotelid.HotelIdModel
import com.example.hbapplicationgroupa.model.customermodule.addcustomerreviewbyhotelid.ReviewByHotelIdResponseModel
import com.example.hbapplicationgroupa.model.customermodule.getCustomerBooking.GetCustomerBookingResponse
import com.example.hbapplicationgroupa.model.customermodule.getcustomerbookingbyuserid.BookingByUserIdResponseModel
import com.example.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber.WishlistByPageNumberResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.allhotels.GetAllHotelsResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.allhotels.PageItem
import com.example.hbapplicationgroupa.model.usermodule.updateuserbyid.UpdateUserByIdModel
import com.example.hbapplicationgroupa.model.usermodule.updateuserbyid.UpdateUserByIdResponseModel
import com.example.hbapplicationgroupa.network.CustomerModuleApiInterface
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
        pageSize: Int,
        pageNumber: Int
    ): Response<WishlistByPageNumberResponseModel> {
        return customerModuleApiInterface.getCustomerWishListByPageNumber(token, pageNumber, pageSize)
    }

    override suspend fun updateUser(
        authToken: String,
        updateUserModel: UpdateUserByIdModel
    ): Response<UpdateUserByIdResponseModel> {
        return customerModuleApiInterface.updateUser(authToken, updateUserModel)
    }

}