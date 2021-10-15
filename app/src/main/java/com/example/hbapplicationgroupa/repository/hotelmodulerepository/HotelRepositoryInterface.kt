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
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query

interface HotelRepositoryInterface {
    suspend fun getHotelById(hotelId: String): Response<GetHotelByIdResponseModel>

    suspend fun getTopHotels(): Response<GetTopHotelsResponseModel>

    suspend fun getTopDeals():Response<GetTopDealsResponseModel>

    suspend fun getTopDealss(pageSize: Int, pageNumber: Int):Response<GetTopDealsResponseModel>

    suspend fun getAllHotels(
        Page: Int,
        pageSize: Int,
        IsBooked: Boolean,
        Price: Double,
        id: String
    ): Response<GetAllHotelsResponseModel>

    suspend fun getHotelRoomsByPrice(
        id: String,
        pageSize: Int,
        pageNumber: Int,
        isBooked: Boolean,
        price: Double
    ): Response<GetHotelRoomsByPriceResponseModel>

    suspend fun getHotelRoomsByAvailability(
        pageSize: Int,
        pageNumber: Int,
        isBooked: Boolean,
    ): Response<GetHotelRoomsByVacancyResponseModel>

    suspend fun getHotelRoomById(roomId: String): Response<GetHotelRoomByIdResponseModel>

    suspend fun getHotelRatings(hotelId: String): Response<GetHotelRatingsResponseModel>

    suspend fun getHotelAmenities(hotelId: String): Response<GetHotelAmenitiesResponseModel>
}