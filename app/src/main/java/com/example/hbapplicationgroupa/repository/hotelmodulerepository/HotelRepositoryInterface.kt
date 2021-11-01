package com.example.hbapplicationgroupa.repository.hotelmodulerepository

import androidx.lifecycle.LiveData
import com.example.hbapplicationgroupa.model.hotelmodule.allhotels.GetAllHotelsResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.bookhotel.BookHotel
import com.example.hbapplicationgroupa.model.hotelmodule.bookhotel.BookHotelResponse
import com.example.hbapplicationgroupa.model.hotelmodule.bookhotel.VerifyBooking
import com.example.hbapplicationgroupa.model.hotelmodule.filterallhotelbylocation.FilterAllHotelByLocation
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelamenities.GetHotelAmenitiesResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseItemData
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelratings.GetHotelRatingsResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelroombyid.GetHotelRoomByIdResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelroomsbyprice.GetHotelRoomsByPriceResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelroomsbyvacancy.GetHotelRoomsByVacancyResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseModel
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
    suspend fun getTopHotels(): Response<GetTopHotelsResponseModel>

    suspend fun getTopDeals(): Response<GetTopDealsResponseModel>


    suspend fun getTopDealss(pageSize: Int, pageNumber: Int): Response<GetTopDealsResponseModel>
//    suspend fun getTopHotels(): Response<GetTopHotelsResponseModel>
//    suspend fun getTopDeals():Response<GetTopDealsResponseModel>

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


    //------- Filter All Hotel By Location
    suspend fun filterAllHotelByLocation(location: String, pageSize: Int, pageNumber: Int): Response<FilterAllHotelByLocation>

    suspend fun pushBookHotel(authToken: String, bookHotelInfo: BookHotel): Response<BookHotel>

    suspend fun getHotelRoomIdByRoomType(hotelId: String, roomTypeId: String): Response<GetHotelRoomByIdResponseModel>

    suspend fun pushPaymentTransactionDetails(verifyBooking: VerifyBooking): Response<VerifyBooking>

}