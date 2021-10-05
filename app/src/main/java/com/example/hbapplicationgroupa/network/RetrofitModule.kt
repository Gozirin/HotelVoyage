package com.example.hbapplicationgroupa.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitModule {

    private const val BASE_URL = ""

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
    val hotelBookingApi: HotelBookingApi by lazy {
        retrofit.create(HotelBookingApi::class.java)
    }
}