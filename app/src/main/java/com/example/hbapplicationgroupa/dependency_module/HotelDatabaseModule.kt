package com.example.hbapplicationgroupa.dependency_module


import android.content.Context
import androidx.room.Room
import com.example.hbapplicationgroupa.database.HotelDatabase
import com.example.hbapplicationgroupa.database.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HotelDatabaseModule {

    @Singleton
    @Provides
    fun provideGetDbInstance(@ApplicationContext context: Context): HotelDatabase {
        return Room.databaseBuilder(context, HotelDatabase::class.java, "hotelDb")
            .build()
    }

    @Singleton
    @Provides
    fun provideGetWishListDao(hotelDatabase: HotelDatabase): WishListDao {
        return hotelDatabase.getWishListDao()
    }

    @Singleton
    @Provides
    fun provideGetCustomersBookingDao(hotelDatabase: HotelDatabase): CustomerBookingDao {
        return hotelDatabase.getCustomerBookingDao()
    }

    @Singleton
    @Provides
    fun provideGetAmenitiesDao(hotelDatabase: HotelDatabase): AmenitiesDao {
        return hotelDatabase.getAmenitiesDao()
    }

    @Singleton
    @Provides
    fun provideGetHotelRoomByIdDao(hotelDatabase: HotelDatabase): HotelRoomByIdDao {
        return hotelDatabase.getHotelRoomByIdDao()
    }

    @Singleton
    @Provides
    fun provideGetHotelDataDao(hotelDatabase: HotelDatabase): HotelDao {
        return hotelDatabase.getHotelDataDao()
    }

    @Singleton
    @Provides
    fun provideGetHotelRatingsDao(hotelDatabase: HotelDatabase): HotelRatingsDao {
        return hotelDatabase.getHotelRatingsDao()
    }

    @Singleton
    @Provides
    fun provideGetUserHotelDao(hotelDatabase: HotelDatabase): UserHotelDao {
        return hotelDatabase.getUserHotelDao()
    }
}