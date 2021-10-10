package com.example.hbapplicationgroupa.dependency_module

import com.example.hbapplicationgroupa.network.HBAuthenticationModuleApi
import com.example.hbapplicationgroupa.network.hotelbookingapi.HBAdminModuleApi
import com.example.hbapplicationgroupa.network.hotelbookingapi.HBAmenitiesModuleApi
import com.example.hbapplicationgroupa.network.hotelbookingapi.HBCustomerModuleApi
import com.example.hbapplicationgroupa.network.hotelbookingapi.HBHotelModuleApi
import com.example.hbapplicationgroupa.utils.BASE_URL
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
    @Singleton
    @Provides
    //get retrofit instance
//    fun retrofit(): Retrofit {
//        //okhttp interceptor is used to to log retrofit responses especially when debugging.
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        val client = OkHttpClient.Builder()
//            .addInterceptor(interceptor)
//            .build()
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//    }
    fun retrofit(): Retrofit {
        //okhttp interceptor is used to to log retrofit responses especially when debugging.
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
            .build()
    }


    //get api instance from retrofit
    @Singleton
    @Provides
    fun provideHBAdminModuleApi(): HBAdminModuleApi {
        return retrofit().create(HBAdminModuleApi::class.java)
    }

    @Singleton
    @Provides
    fun provideHBAmenitiesModuleApi(): HBAmenitiesModuleApi {
        return retrofit().create(HBAmenitiesModuleApi::class.java)
    }

    @Singleton
    @Provides
    fun provideHBAuthenticationModuleApi(): HBAuthenticationModuleApi {
        return retrofit().create(HBAuthenticationModuleApi::class.java)
    }

    @Singleton
    @Provides
    fun provideHBCustomerModuleApi(): HBCustomerModuleApi {
        return retrofit().create(HBCustomerModuleApi::class.java)
    }

    @Singleton
    @Provides
    fun provideHBHotelModuleApi(): HBHotelModuleApi {
        return retrofit().create(HBHotelModuleApi::class.java)
    }
}




