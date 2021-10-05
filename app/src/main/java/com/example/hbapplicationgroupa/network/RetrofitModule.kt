package com.example.hbapplicationgroupa.network

import com.example.hbapplicationgroupa.network.hotelbookingapi.HBAdminModuleApi
import com.example.hbapplicationgroupa.network.hotelbookingapi.HBAmenitiesModuleApi
import com.example.hbapplicationgroupa.network.hotelbookingapi.HBCustomerModuleApi
import com.example.hbapplicationgroupa.network.hotelbookingapi.HBHotelModuleApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitModule {

    private const val BASE_URL = "https://<base_url>/api/v1/"

    //get retrofit instance
    private val retrofit by lazy {
        //okhttp interceptor is used to to log retrofit responses especially when debugging.
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }


    //get api instance from retrofit


    val hbAdminModuleApi: HBAdminModuleApi by lazy {
        retrofit.create(HBAdminModuleApi::class.java)
    }

    val hbAmenitiesModuleApi: HBAmenitiesModuleApi by lazy {
        retrofit.create(HBAmenitiesModuleApi::class.java)
    }

    val hbAuthenticationModuleApi: HBAuthenticationModuleApi by lazy {
        retrofit.create(HBAuthenticationModuleApi::class.java)
    }

    val hbCustomerModuleApi: HBCustomerModuleApi by lazy {
        retrofit.create(HBCustomerModuleApi::class.java)
    }

    val hbHotelModuleApi: HBHotelModuleApi by lazy {
        retrofit.create(HBHotelModuleApi::class.java)
    }
}