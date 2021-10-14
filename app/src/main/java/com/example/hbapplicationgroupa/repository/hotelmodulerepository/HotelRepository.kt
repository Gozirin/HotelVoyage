package com.example.hbapplicationgroupa.repository.hotelmodulerepository

import com.example.hbapplicationgroupa.model.hotelmodule.getallhotels.GetAllHotelsResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelamenities.GetHotelAmenitiesResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelratings.GetHotelRatingsResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelroombyid.GetHotelRoomByIdResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelroomsbyprice.GetHotelRoomsByPriceResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelroomsbyvacancy.GetHotelRoomsByVacancyResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseModel
import com.example.hbapplicationgroupa.network.HotelModuleApiInterface
import retrofit2.Response
import javax.inject.Inject

class HotelRepository @Inject constructor(private val hotelModuleApiInterface: HotelModuleApiInterface): HotelRepositoryInterface {
    override suspend fun getHotelById(hotelId: String): Response<GetHotelByIdResponseModel> {
        return hotelModuleApiInterface.getHotelById(hotelId)
    }

    override suspend fun getTopHotels(): Response<GetTopHotelsResponseModel> {
        return hotelModuleApiInterface.getTopHotels()
    }

    override suspend fun getTopDeals(): Response<GetTopDealsResponseModel> {
        return hotelModuleApiInterface.getTopDeals()
    }

    override suspend fun getAllHotels(
        Page: Int,
        pageSize: Int,
        IsBooked: Boolean,
        Price: Double,
        id: String
    ): Response<GetAllHotelsResponseModel> {
        return hotelModuleApiInterface.getAllHotels(Page, pageSize, IsBooked, Price, id)
    }

    override suspend fun getHotelRoomsByPrice(
        id: String,
        pageSize: Int,
        pageNumber: Int,
        isBooked: Boolean,
        price: Double
    ): Response<GetHotelRoomsByPriceResponseModel> {
        return hotelModuleApiInterface.getHotelRoomsByPrice(id, pageSize, pageNumber, isBooked, price)
    }

    override suspend fun getHotelRoomsByAvailability(
        pageSize: Int,
        pageNumber: Int,
        isBooked: Boolean
    ): Response<GetHotelRoomsByVacancyResponseModel> {
        return hotelModuleApiInterface.getHotelRoomsByAvailability(pageSize, pageNumber, isBooked)
    }

    override suspend fun getHotelRoomById(roomId: String): Response<GetHotelRoomByIdResponseModel> {
        return hotelModuleApiInterface.getHotelRoomById(roomId)
    }

    override suspend fun getHotelRatings(hotelId: String): Response<GetHotelRatingsResponseModel> {
        return hotelModuleApiInterface.getHotelRatings(hotelId)
    }

    override suspend fun getHotelAmenities(hotelId: String): Response<GetHotelAmenitiesResponseModel> {
        return hotelModuleApiInterface.getHotelAmenities(hotelId)
    }
}