package com.example.hbapplicationgroupa.network

import com.example.hbapplicationgroupa.network.hotelbookingapi.HBAdminModuleApi
import com.example.hbapplicationgroupa.network.hotelbookingapi.HBAmenitiesModuleApi
import com.example.hbapplicationgroupa.network.hotelbookingapi.HBCustomerModuleApi
import com.example.hbapplicationgroupa.network.hotelbookingapi.HBHotelModuleApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object RetrofitModule {

    private const val BASE_URL = "https://<base_url>/api/v1/"

    @Singleton
    @Provides
    //get retrofit instance
    fun retrofit(): Retrofit {
        //okhttp interceptor is used to to log retrofit responses especially when debugging.
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }


    //get api instance from retrofit

    @Singleton
    @Provides
    fun createHBAdminModuleApi(): HBAdminModuleApi {
        return retrofit().create(HBAdminModuleApi::class.java)
    }


    @Singleton
    @Provides
    fun createHBAmenitiesModuleApi(): HBAmenitiesModuleApi {
        return retrofit().create(HBAmenitiesModuleApi::class.java)
    }


    @Singleton
    @Provides
    fun createHBAuthenticationModuleApi(): HBAuthenticationModuleApi {
        return retrofit().create(HBAuthenticationModuleApi::class.java)
    }


    @Singleton
    @Provides
    fun createHBCustomerModuleApi(): HBCustomerModuleApi {
        return retrofit().create(HBCustomerModuleApi::class.java)
    }


    @Singleton
    @Provides
    fun createHBHotelModuleApi(): HBHotelModuleApi {
        return retrofit().create(HBHotelModuleApi::class.java)
    }
}




