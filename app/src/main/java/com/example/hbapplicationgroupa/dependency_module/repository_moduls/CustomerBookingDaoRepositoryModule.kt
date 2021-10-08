package com.example.hbapplicationgroupa.dependency_module.repository_moduls

import com.example.hbapplicationgroupa.database.dao.CustomerBookingDao
import com.example.hbapplicationgroupa.network.hotelbookingapi.HBCustomerModuleApi
import com.example.hbapplicationgroupa.repository.customerBookingDaoRepository.CustomerBookingDaoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CustomerBookingDaoRepositoryModule {

    @Singleton
    @Provides
    fun provideCustomerBookingDaoRepository (api: HBCustomerModuleApi, dao: CustomerBookingDao): CustomerBookingDaoRepository {
        return CustomerBookingDaoRepository(api, dao)
    }
}