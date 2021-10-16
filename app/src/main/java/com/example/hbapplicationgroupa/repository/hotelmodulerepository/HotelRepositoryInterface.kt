package com.example.hbapplicationgroupa.repository.hotelmodulerepository

import androidx.lifecycle.LiveData
import com.example.hbapplicationgroupa.model.hotelmodule.getallhotels.GetAllHotelsResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelamenities.GetHotelAmenitiesResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseItemData
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelratings.GetHotelRatingsResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelroombyid.GetHotelRoomByIdResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelroomsbyprice.GetHotelRoomsByPriceResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelroomsbyvacancy.GetHotelRoomsByVacancyResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseItem
import com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseItem
import com.example.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseModel
import retrofit2.Response

/*
HotelRepositoryInterface holds functions that manipulates data between the local database and the remote database
 */

interface HotelRepositoryInterface {
    //-----------------Hotel description-----------------
    suspend fun getHotelDescriptionFromApi(hotelId: String)
    fun getHotelDescriptionFromDb(): LiveData<List<GetHotelByIdResponseItemData>>
    suspend fun saveHotelDescriptionToDb(hotel: GetHotelByIdResponseItemData)
    suspend fun deleteHotelDescriptionFromDb(hotel: GetHotelByIdResponseItemData)

    //--------------------------------------------------------------------

    suspend fun getTopHotels():Response<GetTopHotelsResponseModel>

    suspend fun getTopDeals(): Response<GetTopDealsResponseModel>
    suspend fun getTopHotels(): Response<GetTopHotelsResponseModel>
    suspend fun getTopDeals():Response<GetTopDealsResponseModel>

//    suspend fun getTopHotelsFromAPI()
//
//    suspend fun saveTopHotelsToDatabase(topHotelsResponseItem: GetTopHotelsResponseItem)
//
//    suspend fun getTopHotelsFromDatabase(): LiveData<List<GetTopHotelsResponseItem>>
//
//    suspend fun getTopDealsFromAPI()
//
//    suspend fun saveTopDealsToDatabase(topDealsResponseItem: GetTopDealsResponseItem)
//
//    suspend fun getTopDealsFromDatabase(): LiveData<List<GetTopDealsResponseItem>>

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