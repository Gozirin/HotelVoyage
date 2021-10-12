package com.example.hbapplicationgroupa.dependencyinjection

import android.app.Application
import com.example.hbapplicationgroupa.database.HotelDatabase
import com.example.hbapplicationgroupa.database.dao.UserByIdDao
import com.example.hbapplicationgroupa.database.dao.UserPhotoByIdDao
import com.example.hbapplicationgroupa.database.dao.WishlistByPageNumberDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HotelDatabaseModule {
    @Singleton
    @Provides
    fun provideDatabaseInstance(context: Application): HotelDatabase{
        return HotelDatabase.getDbInstance(context)
    }

//    @Singleton
//    @Provides
//    fun provideBookingByUserIdDao(hotelDatabase: HotelDatabase): BookingByUserIdDao {
//        return hotelDatabase.getBookingByUserIdDao()
//    }

    @Singleton
    @Provides
    fun provideWishlistByPageNumberDao(hotelDatabase: HotelDatabase): WishlistByPageNumberDao {
        return hotelDatabase.getWishlistByPageNumberDao()
    }

    @Singleton
    @Provides
    fun provideUserByUserIdDao(hotelDatabase: HotelDatabase): UserByIdDao {
        return hotelDatabase.getUserByIdDao()
    }

    @Singleton
    @Provides
    fun provideUserPhotoByIdDao(hotelDatabase: HotelDatabase): UserPhotoByIdDao {
        return hotelDatabase.getUserPhotoByIdDao()
    }
}