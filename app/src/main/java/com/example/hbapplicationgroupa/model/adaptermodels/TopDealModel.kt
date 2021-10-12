package com.example.hbapplicationgroupa.model.adaptermodels

import com.example.hbapplicationgroupa.R

data class TopDealModel(
    val id:Int,
    val name:String,
    val price:Int,
    val classOfHotel: String,
    val rating: String,
    val image : Int
) {
    //Dummy data
    companion object{
        val topDealList = listOf(
            TopDealModel(
                1, "Atlantis Paradise", 6500,
                "9 Star Hotel", "99%", R.drawable.hotel_atlantis_paradise_bahamas
            ),
            TopDealModel(
                2, "Burb Arab", 8500,
                "7 Star Hotel", "100%", R.drawable.hotel_burg_arab_dubai
            )
        )
    }
}