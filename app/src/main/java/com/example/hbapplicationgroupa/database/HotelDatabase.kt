package com.example.hbapplicationgroupa.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hbapplicationgroupa.models.customerModule.customerBookingsModel.CustomerBookingsData
import com.example.hbapplicationgroupa.models.customerModule.customerWishlistModel.CustomerWishListData
import com.example.hbapplicationgroupa.models.hotelModule.allHotelsModel.AllHotelsData
import com.example.hbapplicationgroupa.models.hotelModule.amenities.AmenitiesData
import com.example.hbapplicationgroupa.models.hotelModule.hotelAmenitiesModel.HotelAmenitiesData
import com.example.hbapplicationgroupa.models.hotelModule.hotelModel.HotelData
import com.example.hbapplicationgroupa.models.hotelModule.hotelRatingsModel.HotelRatingsData
import com.example.hbapplicationgroupa.models.hotelModule.hotelReviewsModel.HotelReviewsData
import com.example.hbapplicationgroupa.models.hotelModule.hotelRoomByIdModel.HotelRoomByIdData
import com.example.hbapplicationgroupa.models.hotelModule.hotelRoomsByPriceModel.HotelRoomsByPriceData
import com.example.hbapplicationgroupa.models.hotelModule.hotelRoomsVacancyModel.HotelRoomsVacancyData
import com.example.hbapplicationgroupa.models.hotelModule.hotelTransactionsModel.HotelTransactionsData
import com.example.hbapplicationgroupa.models.hotelModule.topDealsModel.TopDealsData
import com.example.hbapplicationgroupa.models.hotelModule.topHotelsModel.TopHotelsData
import com.example.hbapplicationgroupa.models.hotelModule.userHotelsModel.UserHotelData
import com.example.hbapplicationgroupa.models.hotelModule.userTransactionsModel.UserTransactionsData

@Database(entities = [
    CustomerWishListData::class,
    CustomerBookingsData::class,
    AllHotelsData::class,
    AmenitiesData::class,
    HotelAmenitiesData::class,
    HotelRoomByIdData::class,
    HotelData::class,
    HotelRatingsData::class,
    HotelReviewsData::class,
    HotelRoomByIdData::class,
    HotelRoomsByPriceData::class,
    HotelRoomsVacancyData::class,
    HotelTransactionsData::class,
    TopDealsData::class,
    TopHotelsData::class,
    UserHotelData::class,
    UserTransactionsData::class
], version = 1, exportSchema = false)
abstract class HotelDatabase: RoomDatabase() {
    abstract fun getDaoInstance(): HotelDao

    //Creating a single instance of the database
    companion object {
        private var dbInstance: HotelDatabase? = null

        fun getDbInstance(context: Context): HotelDatabase{
            if (dbInstance == null){
                dbInstance = Room.databaseBuilder(context.applicationContext, HotelDatabase::class.java, "hotelDb")
                    .allowMainThreadQueries()
                    .build()
            }

            return dbInstance!!
        }
    }
}