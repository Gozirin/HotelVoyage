package com.example.hbapplicationgroupa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.model.HotelGalleryModel

/**
 * This is the Adapter for hotel_desc_viewPager
 */
class HotelGalleryAdapter : RecyclerView.Adapter<HotelGalleryAdapter.GalleryViewHolder>() {
    var galleryList: ArrayList<HotelGalleryModel> = arrayListOf()
    class GalleryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val galleryImage: ImageView = view.findViewById(R.id.hotel_gallery_iv)

        //Method hotel_desc_viewPager data with HotelGalleryModel data
        fun bindGalleryImage(image: HotelGalleryModel){
            galleryImage.setImageResource(image.galleryImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.hotel_gallery_viewpager, parent, false)
        return GalleryViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bindGalleryImage(galleryList[position])
    }

    override fun getItemCount(): Int {
        return galleryList.size
    }
}