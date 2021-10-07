package com.example.hbapplicationgroupa.adapter.topHotel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupa.model.Hotel
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.databinding.TopHotelRecyclerviewViewItemBinding


class TopHotelAdapter(private var adapterList : List<Hotel>,
                        private val topHotelsItemClickListener: TopHotelsItemClickListener,
                            private val topHotelsBookBtnClickListener: TopHotelsBookBtnClickListener)
    : RecyclerView.Adapter<TopHotelAdapter.ViewHolder>(){

    val binding: TopHotelRecyclerviewViewItemBinding? = null

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nameOfHotel = binding?.topHotelRecyclerviewHotelName
        val priceOfHotel = binding?.topHotelRecyclerviewHotelPrice
        val classOfHotel = binding?.topHotelRecyclerviewHotelStatus
        val ratingOfHotel = binding?.topHotelRecyclerviewHotelRating
        val imageOfHotel = binding?.topHotelRecyclerviewImageview
        val hotelBookBtn: AppCompatButton = itemView.findViewById(R.id.topHotelBookBtn)
        val topHotelView: CardView = itemView.findViewById(R.id.topHotelView)

    }

    interface TopHotelsItemClickListener {
        fun topHotelsItemClicked(position: Int)
    }

    interface TopHotelsBookBtnClickListener {
        fun topHotelsBookBtnClicked(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.top_hotel_recyclerview_view_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameOfHotel?.text = adapterList[position].name
        holder.priceOfHotel?.text = adapterList[position].price.toString()
        holder.classOfHotel?.text = adapterList[position].classOfHotel
        holder.ratingOfHotel?.text = adapterList[position].rating
        holder.imageOfHotel?.setImageResource(adapterList[position].image)

        holder.topHotelView?.setOnClickListener {
            topHotelsItemClickListener.topHotelsItemClicked(position)
        }

        holder.hotelBookBtn?.setOnClickListener {
            topHotelsBookBtnClickListener.topHotelsBookBtnClicked(position)
        }


    }

    override fun getItemCount(): Int {
       return adapterList.size
    }
}