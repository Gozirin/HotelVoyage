package com.example.hbapplicationgroupa.adapter.allHotelsAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.model.hotelmodule.allhotels.PageItem


class AllHotelsAdapter(
        val allHotelsItemClickListener: AllHotelsItemClickListener,
        val allHotelsBookBtnClickListener: AllHotelsBookBtnClickListener
): RecyclerView.Adapter<AllHotelsAdapter.AllHotelsViewHolder>() {

    var listOfAllHotels: MutableList<PageItem> = mutableListOf()


    inner class AllHotelsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nameOfAllHotel: TextView = itemView.findViewById(R.id.allHotels_recyclerview_hotel_name)
        val priceOfAllHotel: TextView = itemView.findViewById(R.id.allHotels_recyclerview_hotel_price)
        val ratingOfAllHotel: TextView = itemView.findViewById(R.id.allHotels_recyclerview_hotel_rating)
        val imageOfAllHotel: ImageView = itemView.findViewById(R.id.allHotels_recyclerview_imageview)
        val allHotelBookBtn: Button = itemView.findViewById(R.id.allHotelsBookBtn)
        val allHotelView: TextView = itemView.findViewById(R.id.allHotelsView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllHotelsViewHolder {
        return AllHotelsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_all_hotels_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AllHotelsViewHolder, position: Int) {
        holder.nameOfAllHotel.text = listOfAllHotels[position].name
        holder.priceOfAllHotel.text = listOfAllHotels[position].roomTypes?.get(0)?.price.toString()
        holder.ratingOfAllHotel.text = listOfAllHotels[position].rating.toString()
        holder.imageOfAllHotel.let {
            Glide.with(it.context)
                .load(listOfAllHotels[position].featuredImage)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imageOfAllHotel)
        }
        holder.allHotelView.setOnClickListener{
            allHotelsItemClickListener.allHotelsItemClicked(position)
        }
        holder.allHotelBookBtn.setOnClickListener{
            allHotelsBookBtnClickListener.allHotelsBookBtnClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return listOfAllHotels.size
    }

    interface AllHotelsItemClickListener {
        fun allHotelsItemClicked(position: Int)
    }

    interface AllHotelsBookBtnClickListener {
        fun allHotelsBookBtnClicked(position: Int)
    }

//    fun setList(list: List<PageItem>){
//        listOfAllHotels.addAll(list)
//    }
}