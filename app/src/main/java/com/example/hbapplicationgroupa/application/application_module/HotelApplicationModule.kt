package com.example.hbapplicationgroupa.application.application_module



import com.example.hbapplicationgroupa.network.HotelRetrofitServices
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HotelApplicationModule {
    
    //this function is used to setup the retrofit dependency
    @Singleton
    @Provides
    fun provideHotelRetrofitService(): HotelRetrofitServices {
        return Retrofit.Builder()
            .baseUrl("https://<base_url>/api/v1/Admin/get-statistics")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(HotelRetrofitServices::class.java)
    }
}