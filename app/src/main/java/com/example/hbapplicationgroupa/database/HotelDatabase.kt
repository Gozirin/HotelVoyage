package com.example.hbapplicationgroupa.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hbapplicationgroupa.database.dao.*
import com.example.hbapplicationgroupa.models.customerModule.customerBookingsModel.CustomerBookingsData
import com.example.hbapplicationgroupa.models.customerModule.customerWishlistModel.CustomerWishListData
import com.example.hbapplicationgroupa.models.hotelModule.allHotelsModel.AllHotelsData
import com.example.hbapplicationgroupa.models.hotelModule.amenities.AmenitiesData
import com.example.hbapplicationgroupa.models.hotelModule.hotelModel.HotelData
import com.example.hbapplicationgroupa.models.hotelModule.hotelRatingsModel.HotelRatingsData
import com.example.hbapplicationgroupa.models.hotelModule.hotelReviewsModel.HotelReviewsData
import com.example.hbapplicationgroupa.models.hotelModule.hotelRoomByIdModel.HotelRoomByIdData
import com.example.hbapplicationgroupa.models.hotelModule.hotelTransactionsModel.HotelTransactionsData
import com.example.hbapplicationgroupa.models.hotelModule.userHotelsModel.UserHotelData
import com.example.hbapplicationgroupa.models.hotelModule.userTransactionsModel.UserTransactionsData

//AllHotelsData Not clear about this
//HotelAmenitiesData similar to AmenitiesData
//HotelReviewsData check route authorization again
//HotelTransactionsData check route authorization again
//UserTransactionsData check route authorization again

@Database(entities = [
    CustomerWishListData::class,
    CustomerBookingsData::class,
    AllHotelsData::class,
    AmenitiesData::class,
    HotelRoomByIdData::class,
    HotelData::class,
    HotelRatingsData::class,
    HotelReviewsData::class,
    HotelTransactionsData::class,
    UserHotelData::class,
    UserTransactionsData::class
], version = 1, exportSchema = false)
abstract class HotelDatabase: RoomDatabase() {
    abstract fun getWishListDao(): WishListDao
    abstract fun getCustomerBookingDao(): CustomerBookingDao
    abstract fun getAmenitiesDao(): AmenitiesDao
    abstract fun getHotelRoomByIdDao(): HotelRoomByIdDao
    abstract fun getHotelDataDao(): HotelDao
    abstract fun getHotelRatingsDao(): HotelRatingsDao
    abstract fun getUserHotelDao(): UserHotelDao

    //Creating a single instance of the database
    companion object {
        private var dbInstance: HotelDatabase? = null

        fun getDbInstance(context: Context): HotelDatabase{
            if (dbInstance == null){
                dbInstance = Room.databaseBuilder(context.applicationContext, HotelDatabase::class.java, "hotelDb")
                    .build()
            }

            return dbInstance!!
        }
    }
}