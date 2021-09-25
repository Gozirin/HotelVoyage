package com.example.hbapplicationgroupa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupa.R

/**
 * This is the ViewPager Adapter for the id:hotel_desc_services_viewPager
 */
class HotelRoomServiceViewPagerAdapter() : RecyclerView.Adapter<HotelRoomServiceViewPagerAdapter.HotelViewHolder>() {
    class HotelViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.hotel_services_viewpager_layout, parent, false)
        return HotelViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}