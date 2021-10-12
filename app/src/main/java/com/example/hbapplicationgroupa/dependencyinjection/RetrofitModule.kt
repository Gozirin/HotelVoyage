package com.example.hbapplicationgroupa.dependencyinjection

import com.example.hbapplicationgroupa.network.AuthModuleApiInterface
import com.example.hbapplicationgroupa.network.CustomerModuleApiInterface
import com.example.hbapplicationgroupa.network.HotelModuleApiInterface
import com.example.hbapplicationgroupa.network.UserModuleApiInterface
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
class RetrofitModule {
    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit {
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
    fun provideAuthModuleApi(): AuthModuleApiInterface {
        return provideRetrofitInstance().create(AuthModuleApiInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideCustomerModuleApi(): CustomerModuleApiInterface {
        return provideRetrofitInstance().create(CustomerModuleApiInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideHotelModuleApi(): HotelModuleApiInterface {
        return provideRetrofitInstance().create(HotelModuleApiInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideUserModuleApi(): UserModuleApiInterface {
        return provideRetrofitInstance().create(UserModuleApiInterface::class.java)
    }
}



