package com.example.hbapplicationgroupa.repository.hotelmodulerepository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.hbapplicationgroupa.database.dao.HotelByIdDao
import com.example.hbapplicationgroupa.model.hotelmodule.getallhotels.GetAllHotelsResponseItem

import com.example.hbapplicationgroupa.model.hotelmodule.allhotels.GetAllHotelsResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.filterallhotelbylocation.FilterAllHotelByLocation
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelamenities.GetHotelAmenitiesResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseItemData
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelratings.GetHotelRatingsResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelroombyid.GetHotelRoomByIdResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelroomsbyprice.GetHotelRoomsByPriceResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelroomsbyvacancy.GetHotelRoomsByVacancyResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseModel
import com.example.hbapplicationgroupa.network.HotelModuleApiInterface
import retrofit2.Response
import javax.inject.Inject

/*
HotelRepository is provided with hotelModuleApiInterface to communicate with remote data source
and DAOs to communicate with the local room database for data manipulation on the UI
 */

class HotelRepository @Inject constructor(
    private val hotelModuleApiInterface: HotelModuleApiInterface,
    private val hotelByIdDao: HotelByIdDao,
    ): HotelRepositoryInterface {

    //-----------------Hotel description-----------------
    //getHotelDescriptionFromApi() makes a request to fetch an hotel's description.
    //If the request is successful, the fetched hotel description is added to the database.
    override suspend fun getHotelDescriptionFromApi(hotelId: String) {
        try {
            val response = hotelModuleApiInterface.getHotelById(hotelId)
            val hotelDescription = response.body()?.data
            val statusCode = response.body()?.statusCode
            val message = response.body()?.message

            if (response.isSuccessful){
                if (hotelDescription != null) {
                    saveHotelDescriptionToDb(hotelDescription)
                }else{
                    Log.d("GKB", "getHotelByIdFromApi: hotelDescription is null. Status code = $statusCode. Message = $message")
                }
            }else{
                Log.d("GKB", "getHotelByIdFromApi: Response failed. Status code = $statusCode. Message = $message")
            }
        }catch (e: Exception){
            Log.d("GKB", "getHotelDescriptionFromApi: ${e.message}")
        }
    }

    override fun getHotelDescriptionFromDb(): LiveData<List<GetHotelByIdResponseItemData>> {
        return hotelByIdDao.getHotelById()
    }

    override suspend fun saveHotelDescriptionToDb(hotel: GetHotelByIdResponseItemData) {
        hotelByIdDao.insertHotel(hotel)
    }

    override suspend fun deleteHotelDescriptionFromDb(hotel: GetHotelByIdResponseItemData) {
        hotelByIdDao.removeHotel(hotel)
    }

    //-----------------------------------------------------------

    override suspend fun getTopHotels(): Response<GetTopHotelsResponseModel> = hotelModuleApiInterface.getTopHotels()

    override suspend fun getTopDeals(): Response<GetTopDealsResponseModel> = hotelModuleApiInterface.getTopDeals()
    override suspend fun getTopDealss(
        pageSize: Int,
        pageNumber: Int
    ): Response<GetTopDealsResponseModel> {
        return hotelModuleApiInterface.getTopDealss(pageSize, pageNumber)
    }

    override suspend fun getAllHotels(
//        PageNumber: Int,
//        pageSize: Int,
    ): Response<GetAllHotelsResponseModel> {
        val result = hotelModuleApiInterface.getAllHotels()
        Log.d("API Calls:", result.toString())
        return result
    }


//    override suspend fun getTopHotelsFromAPI() {
//        hotelModuleApiInterface.getTopHotels()
//    }
//
//    override suspend fun saveTopHotelsToDatabase(topHotelsResponseItem: GetTopHotelsResponseItem) {
//        topHotelsDao.addTopHotels(topHotelsResponseItem)
//    }
//
//    override suspend fun getTopHotelsFromDatabase(): LiveData<List<GetTopHotelsResponseItem>> {
//       return topHotelsDao.getTopHotels()
//    }
//
//    override suspend fun getTopDealsFromAPI() {
//        hotelModuleApiInterface.getTopDeals()
//    }
//
//    override suspend fun saveTopDealsToDatabase(topDealsResponseItem: GetTopDealsResponseItem) {
//        topDealsDao.addTopDeals(topDealsResponseItem)
//    }
//
//    override suspend fun getTopDealsFromDatabase(): LiveData<List<GetTopDealsResponseItem>> {
//        return topDealsDao.getTopDeals()
//    }

//    override suspend fun getTopHotelsFromDatabse() = topHotelsDao.getTopHotels()
//
//
//    override suspend fun getTopDeals() = hotelModuleApiInterface.getTopDeals()


//    override suspend fun getAllHotels(
//        Page: Int,
//        pageSize: Int,
//        IsBooked: Boolean,
//        Price: Double,
//        id: String
//    ) = hotelModuleApiInterface.getAllHotels(Page, pageSize, IsBooked, Price, id)


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


    //------- To Filter All Hotel By Location
    override suspend fun filterAllHotelByLocation(
        location: String,
        pageSize: Int,
        pageNumber: Int
    ): Response<FilterAllHotelByLocation> {
        return hotelModuleApiInterface.filterALlHotelByLocation(location, pageSize, pageNumber)
    }
}