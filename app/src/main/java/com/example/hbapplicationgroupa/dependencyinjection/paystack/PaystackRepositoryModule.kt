package com.example.hbapplicationgroupa.dependencyinjection.paystack

import com.example.hbapplicationgroupa.network.AuthModuleApiInterface
import com.example.hbapplicationgroupa.network.PaystackApiInterface
import com.example.hbapplicationgroupa.repository.authmodulerepository.AuthRepository
import com.example.hbapplicationgroupa.repository.authmodulerepository.AuthRepositoryInterface
import com.example.hbapplicationgroupa.repository.paystackrepository.PaystackRepository
import com.example.hbapplicationgroupa.repository.paystackrepository.PaystackRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PaystackRepositoryModule {

    @Singleton
    @Provides
    fun providePaystackRepository(paystackApiInterface: PaystackApiInterface): PaystackRepositoryInterface {
        return PaystackRepository(paystackApiInterface)
    }

}