package com.example.hbapplicationgroupa.dependency_module.repository_moduls

import com.example.hbapplicationgroupa.database.dao.HotelRoomByIdDao
import com.example.hbapplicationgroupa.network.HBAuthenticationModuleApi
import com.example.hbapplicationgroupa.repository.hotelRoomByIdDaoRepository.HotelRoomByIdRepositoryInterface
import com.example.hbapplicationgroupa.repository.hotelRoomByIdDaoRepository.HotelRoomDaoByIdRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HotelRoomDaoByIdRepositoryModule {

    @Singleton
    @Provides
    fun provideHotelRoomDaoByIdRepository(
        hHotelRoomDaoByIdRepositorye: HBAuthenticationModuleApi,
        hotelRoomByIdDao: HotelRoomByIdDao
    ): HotelRoomDaoByIdRepository {
        return HotelRoomDaoByIdRepository(hHotelRoomDaoByIdRepositorye,hotelRoomByIdDao)
    }
}