package com.example.hbapplicationgroupa.repository.amenitiesDaoRepository


import androidx.lifecycle.LiveData
import com.example.hbapplicationgroupa.database.dao.AmenitiesDao
import com.example.hbapplicationgroupa.models.hotelModule.amenities.AmenitiesData
import com.example.hbapplicationgroupa.models.hotelModule.hotelAmenitiesModel.HotelAmenities
import com.example.hbapplicationgroupa.network.hotelbookingapi.HBAmenitiesModuleApi
import retrofit2.Response
import javax.inject.Inject

class AmenitiesDaoRepository(val api: HBAmenitiesModuleApi, val responseDao: AmenitiesDao):
    AmenitiesDaoRepositoryInterface {



    override suspend fun getAmenitiesById(id: String): LiveData<List<AmenitiesData>> {
        return responseDao.getAmenitiesById(id)
    }

    override fun addAmenities(amenitiesData: AmenitiesData) {
       responseDao.addAmenities(amenitiesData)
    }

    override suspend fun getHotelAmenities(hotelId: String): Response<HotelAmenities> {
        return api.getHotelAmenities(hotelId)
    }


}