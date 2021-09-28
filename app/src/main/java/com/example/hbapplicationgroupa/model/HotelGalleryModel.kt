package com.example.hbapplicationgroupa.model

import com.example.hbapplicationgroupa.R

data class HotelGalleryModel(
    val galleryImage: Int
){
    companion object{
        val galleryList = arrayListOf(
            HotelGalleryModel(R.drawable.deluxeroom),
            HotelGalleryModel(R.drawable.smith),
            HotelGalleryModel(R.drawable.ic_launcher_background),
        )
    }
}