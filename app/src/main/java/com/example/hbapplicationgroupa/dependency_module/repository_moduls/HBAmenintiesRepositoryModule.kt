package com.example.hbapplicationgroupa.dependency_module.repository_moduls

import com.example.hbapplicationgroupa.database.dao.AmenitiesDao
import com.example.hbapplicationgroupa.database.dao.CustomerBookingDao
import com.example.hbapplicationgroupa.network.hotelbookingapi.HBAmenitiesModuleApi
import com.example.hbapplicationgroupa.network.hotelbookingapi.HBCustomerModuleApi
import com.example.hbapplicationgroupa.repository.amenitiesDaoRepository.AmenitiesDaoRepository
import com.example.hbapplicationgroupa.repository.customerBookingDaoRepository.CustomerBookingDaoRepository
import com.example.hbapplicationgroupa.repository.customerBookingDaoRepository.CustomerBookingDaoRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HBAmenintiesRepositoryModule {

    @Singleton
    @Provides
    fun provideAmenitiesDaoRepository(api: HBAmenitiesModuleApi, responseDao: AmenitiesDao): AmenitiesDaoRepository {
     return AmenitiesDaoRepository(api,responseDao)
    }

}