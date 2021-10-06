package com.example.hbapplicationgroupa.models.model

import com.example.hbapplicationgroupa.R

/**
 * Model for HotelGallery Viewpager Adapter
 */
data class HotelGalleryModel(
    val galleryImage: Int
){
    //Fake data to test Viewpager (This can be deleted)
    companion object{
        val galleryList = arrayListOf(
            HotelGalleryModel(R.drawable.deluxeroom),
            HotelGalleryModel(R.drawable.smith),
            HotelGalleryModel(R.drawable.ic_launcher_background),
        )
    }
}