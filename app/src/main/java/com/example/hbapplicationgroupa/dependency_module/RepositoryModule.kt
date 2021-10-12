package com.example.hbapplicationgroupa.dependency_module

import com.example.hbapplicationgroupa.networksss.AuthModuleApiInterface
import com.example.hbapplicationgroupa.networksss.CustomerModuleApiInterface
import com.example.hbapplicationgroupa.networksss.HotelModuleApiInterface
import com.example.hbapplicationgroupa.networksss.UserModuleApiInterface
import com.example.hbapplicationgroupa.repos.authmodulerepository.AuthRepository
import com.example.hbapplicationgroupa.repos.customermodulerepository.CustomerRepository
import com.example.hbapplicationgroupa.repos.hotelmodulerepository.HotelRepository
import com.example.hbapplicationgroupa.repos.usermodulerepository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideAuthRepository(authModuleApiInterface: AuthModuleApiInterface): AuthRepository{
        return AuthRepository(authModuleApiInterface)
    }

    @Singleton
    @Provides
    fun provideCustomerRepository(customerModuleApiInterface: CustomerModuleApiInterface): CustomerRepository{
        return CustomerRepository(customerModuleApiInterface)
    }

    @Singleton
    @Provides
    fun provideHotelRepository(hotelModuleApiInterface: HotelModuleApiInterface): HotelRepository{
        return HotelRepository(hotelModuleApiInterface)
    }

    @Singleton
    @Provides
    fun provideUserRepository(userModuleApiInterface: UserModuleApiInterface): UserRepository{
        return UserRepository(userModuleApiInterface)
    }
}