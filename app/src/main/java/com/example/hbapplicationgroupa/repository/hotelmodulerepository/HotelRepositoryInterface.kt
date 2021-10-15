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
    suspend fun getHotelById(
        @Path("hotelId") hotelId: String
    ): Response<GetHotelByIdResponseModel>

    suspend fun getTopHotels(): Response<GetTopHotelsResponseModel>

    suspend fun getTopDeals():Response<GetTopDealsResponseModel>

    suspend fun getTopDealss(pageSize: Int, pageNumber: Int):Response<GetTopDealsResponseModel>

    suspend fun getAllHotels(
        @Query("Page") Page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("IsBooked") IsBooked: Boolean,
        @Query("Price") Price: Double,
        @Path("id") id: String
    ): Response<GetAllHotelsResponseModel>

    suspend fun getHotelRoomsByPrice(
        @Path("id") id: String,
        @Query("pageSize") pageSize: Int,
        @Query("pageNumber") pageNumber: Int,
        @Query("IsBooked") isBooked: Boolean,
        @Query("price") price: Double
    ): Response<GetHotelRoomsByPriceResponseModel>

    suspend fun getHotelRoomsByAvailability(
        @Query("pageSize") pageSize: Int,
        @Query("pageNumber") pageNumber: Int,
        @Query("IsBooked") isBooked: Boolean,
    ): Response<GetHotelRoomsByVacancyResponseModel>

    suspend fun getHotelRoomById(@Path("roomId") roomId: String): Response<GetHotelRoomByIdResponseModel>

    suspend fun getHotelRatings(@Path("hotelId") hotelId: String): Response<GetHotelRatingsResponseModel>

    suspend fun getHotelAmenities(@Path("hotelId") hotelId: String): Response<GetHotelAmenitiesResponseModel>
}