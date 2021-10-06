package com.example.hbapplicationgroupa.repository

import androidx.lifecycle.LiveData
import com.example.hbapplicationgroupa.database.dao.CustomerBookingDao
import com.example.hbapplicationgroupa.models.customerModule.customerBookingsModel.CustomerBookings
import com.example.hbapplicationgroupa.models.customerModule.customerBookingsModel.CustomerBookingsData
import com.example.hbapplicationgroupa.models.customerModule.customerWishlistModel.CustomerWishList
import com.example.hbapplicationgroupa.models.hotelModule.hotelByIdModel.HotelById
import com.example.hbapplicationgroupa.models.hotelModule.hotelRatingsModel.HotelRatings
import com.example.hbapplicationgroupa.network.hotelbookingapi.HBCustomerModuleApi
import retrofit2.Response
import javax.inject.Inject

class CustomerBookingDaoRepository  @Inject constructor(val api: HBCustomerModuleApi, val dao: CustomerBookingDao): CustomerBookingDaoRepositoryInterface {

    override fun getCustomerBookings(): LiveData<List<CustomerBookingsData>> {
      return dao.getCustomerBookings()
    }


    override fun insertNewBooking(customerBookingsData: CustomerBookingsData) {
        dao.insertNewBooking(customerBookingsData)
    }



    override fun removeABooking(customerBookingsData: CustomerBookingsData) {
        dao.removeABooking(customerBookingsData)
    }



    override suspend fun getCustomerBookingsByUserId(
        userId: String,
        pageNumber: Int,
        pageSize: Int
    ): Response<List<CustomerBookings>> {
       return api.getCustomerBookingsByUserId(userId, pageNumber, pageSize)
    }



    override suspend fun addCustomerRReviewByHotelId(hotelId: String): Response<HotelById> {
        return api.addCustomerRReviewByHotelId(hotelId)
    }



    override suspend fun addCustomerRatingsByHotelId(
        rating: Int,
        hotelId: String
    ): Response<HotelRatings> {
        return api.addCustomerRatingsByHotelId(rating, hotelId)
    }



    override suspend fun getCustomerWishListByPageNumber(
        userId: String,
        pageNumber: Int,
        pageSize: Int
    ): Response<List<CustomerWishList>> {
       return api.getCustomerWishListByPageNumber(userId,pageNumber,pageSize)
    }




    override suspend fun updateCustomerReviewByHotelId(hotelId: String): Response<HotelById> {
      return api.updateCustomerReviewByHotelId(hotelId)
    }
}