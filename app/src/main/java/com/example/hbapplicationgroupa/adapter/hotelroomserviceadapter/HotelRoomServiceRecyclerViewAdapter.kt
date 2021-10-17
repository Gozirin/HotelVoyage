package com.example.hbapplicationgroupa.adapter.hotelroomserviceadapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseItemRoomTypes

/**
 * This is the ViewPager Adapter for the id:hotel_desc_services_viewPager in HotelDescription Fragment
 */
class HotelRoomServiceRecyclerViewAdapter() : RecyclerView.Adapter<HotelRoomServiceRecyclerViewAdapter.HotelViewHolder>() {
    private val roomDataList: ArrayList<GetHotelByIdResponseItemRoomTypes> = ArrayList()

    class HotelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val roomImage: ImageView = view.findViewById(R.id.hotel_desc_room_iv)
        private val roomTitle: TextView = view.findViewById(R.id.hotel_desc_roomTitle_tv)
        private val roomPrice: TextView = view.findViewById(R.id.hotel_desc_valueTv)

        //This method binds HotelViewHolder properties with GetHotelByIdResponseItemRoomTypes data
        fun bindHotelRoomData(roomData: GetHotelByIdResponseItemRoomTypes){
            roomTitle.text = roomData.name
            roomPrice.text = roomData.price.toString()

            Glide.with(itemView)
                .load(roomData.thumbnail)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(roomImage)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addHotelRoomService(roomType: ArrayList<GetHotelByIdResponseItemRoomTypes>){
        roomDataList.clear()
        roomDataList.addAll(roomType)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.hotel_services_recyclerview_layout, parent, false)
        return HotelViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        holder.bindHotelRoomData(roomDataList[position])
    }

    override fun getItemCount(): Int {
        return roomDataList.size
    }
}