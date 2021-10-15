package com.example.hbapplicationgroupa.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hbapplicationgroupa.database.dao.*
import com.example.hbapplicationgroupa.model.customermodule.getcustomerbookingbyuserid.BookingByUserIdResponseItems
import com.example.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber.WishlistByPageNumberResponseItems
import com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseItem
import com.example.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseItem
import com.example.hbapplicationgroupa.model.usermodule.getuserbyid.GetUserByIdResponseItem
import com.example.hbapplicationgroupa.model.usermodule.updateuserphotobyuserid.UpdateUserPhotoByUserIdResponseItem

//TODO: Add past bookings (entity)
@Database(entities = [
    WishlistByPageNumberResponseItems::class,
    GetUserByIdResponseItem::class,
    UpdateUserPhotoByUserIdResponseItem::class
], version = 1, exportSchema = false)
abstract class HotelDatabase: RoomDatabase() {
//    abstract fun getBookingByUserIdDao(): BookingByUserIdDao
//    abstract fun getTopHotelsDao(): TopHotelsDao
//    abstract fun getTopDealsDao(): TopDealsDao
    abstract fun getWishlistByPageNumberDao(): WishlistByPageNumberDao
    abstract fun getUserByIdDao(): UserByIdDao
    abstract fun getUserPhotoByIdDao(): UserPhotoByIdDao

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