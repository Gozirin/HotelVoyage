package com.example.hbapplicationgroupa.dependency_module.repository_moduls

import com.example.hbapplicationgroupa.database.dao.UserHotelDao
import com.example.hbapplicationgroupa.repository.userHotelDaoRepository.UserHotelDaoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserHotelDaoRepositoryModule {
    @Singleton
    @Provides
    fun provideUserHotelDaoRepository (userHotelDao: UserHotelDao): UserHotelDaoRepository {
        return UserHotelDaoRepository(userHotelDao)
    }
}