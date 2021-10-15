package com.example.hbapplicationgroupa.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hbapplicationgroupa.database.dao.*
import com.example.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber.WishlistByPageNumberResponseItems
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseItemData
import com.example.hbapplicationgroupa.model.typeconverter.TypeConverter
import com.example.hbapplicationgroupa.model.usermodule.getuserbyid.GetUserByIdResponseItem
import com.example.hbapplicationgroupa.model.usermodule.updateuserphotobyuserid.UpdateUserPhotoByUserIdResponseItem

//TODO: Add past bookings (entity)
@Database(entities = [
    GetHotelByIdResponseItemData::class,
    WishlistByPageNumberResponseItems::class,
    GetUserByIdResponseItem::class,
    UpdateUserPhotoByUserIdResponseItem::class
], version = 1, exportSchema = false)

@TypeConverters(TypeConverter::class)
abstract class HotelDatabase: RoomDatabase() {
//    abstract fun getBookingByUserIdDao(): BookingByUserIdDao
    abstract fun getHotelByIdDao(): HotelByIdDao
    abstract fun getWishlistByPageNumberDao(): WishlistByPageNumberDao
    abstract fun getUserByIdDao(): UserByIdDao
    abstract fun getUserPhotoByIdDao(): UserPhotoByIdDao

    //Creating a single instance of the database
    companion object{
        @Volatile
        private var dbInstance: HotelDatabase? = null
        private val lock = Any()

        operator fun invoke(context: Context) = dbInstance?: synchronized(lock){
            dbInstance?: getDbInstance(context)
        }

        fun getDbInstance(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            HotelDatabase::class.java,
            "hotelDb"
        ).build()
    }

    /*
    Below is a different way of instantiating our database
     */
    //    companion object {
//        private var dbInstance: HotelDatabase? = null
//
//        fun getDbInstance(context: Context): HotelDatabase{
//            if (dbInstance == null){
//                dbInstance = Room.databaseBuilder(context.applicationContext, HotelDatabase::class.java, "hotelDb")
//                    .build()
//            }
//
//            return dbInstance!!
//        }
//    }
}