package com.example.hbapplicationgroupa.network

import com.example.hbapplicationgroupa.model.hotelmodule.getallhotels.GetAllHotelsResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelamenities.GetHotelAmenitiesResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelratings.GetHotelRatingsResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelroombyid.GetHotelRoomByIdResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelroomsbyprice.GetHotelRoomsByPriceResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelroomsbyvacancy.GetHotelRoomsByVacancyResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseModel
import com.example.hbapplicationgroupa.utils.Resource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HotelModuleApiInterface {
    @GET("api/Hotel/{hotelId}")
    suspend fun getHotelById(
        @Path("hotelId") hotelId: String
    ): Response<GetHotelByIdResponseModel>

    @GET("api/Hotel/top-hotels")
    suspend fun getTopHotels(
//        @Query("pageSize") pageSize: Int = 9,
//        @Query("pageNumber") pageNumber: Int = 1
    ): Response<GetTopHotelsResponseModel>

    @GET("api/Hotel/top-deals")
    suspend fun getTopDeals(
//        @Query("pageSize") pageSize: Int = 9 ,
//        @Query("pageNumber") pageNumber: Int = 1
    ):Response<GetTopDealsResponseModel>

    @GET("api/Hotel/top-deals")
    suspend fun getTopDealss(
        @Query("pageSize") pageSize: Int,
        @Query("pageNumber") pageNumber: Int
    ):Response<GetTopDealsResponseModel>

    @GET("api/v1/Hotels/{Id}/rooms?page={pageNumber}&pageSize={pageSize}&IsBooked={true}&Price={amount}")
    suspend fun getAllHotels(
        @Query("Page") Page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("IsBooked") IsBooked: Boolean,
        @Query("Price") Price: Double,
        @Path("id") id: String
    ): Response<GetAllHotelsResponseModel>

    @GET("api/v1/Hotels/{Id}/rooms?page={pageNumber}&pageSize={pageSize}&IsBooked={true}&Price={amount}")
    suspend fun getHotelRoomsByPrice(
        @Path("id") id: String,
        @Query("pageSize") pageSize: Int,
        @Query("pageNumber") pageNumber: Int,
        @Query("IsBooked") isBooked: Boolean,
        @Query("price") price: Double
    ): Response<GetHotelRoomsByPriceResponseModel>

    @GET("api/v1/Hotels/{Id}/rooms?page={pageNumber}&pageSize={pageSize}&IsBooked={false}")
    suspend fun getHotelRoomsByAvailability(
        @Query("pageSize") pageSize: Int,
        @Query("pageNumber") pageNumber: Int,
        @Query("IsBooked") isBooked: Boolean,
    ): Response<GetHotelRoomsByVacancyResponseModel>

    @GET("api/Hotel/room/{roomId}")
    suspend fun getHotelRoomById(@Path("roomId") roomId: String): Response<GetHotelRoomByIdResponseModel>

    @GET("api/Hotel/{hotelId}/ratings")
    suspend fun getHotelRatings(@Path("hotelId") hotelId: String): Response<GetHotelRatingsResponseModel>

    @GET("api/Hotel/{hotelId}/amenities?page=1&pageSize=5")
    suspend fun getHotelAmenities(@Path("hotelId") hotelId: String): Response<GetHotelAmenitiesResponseModel>
}