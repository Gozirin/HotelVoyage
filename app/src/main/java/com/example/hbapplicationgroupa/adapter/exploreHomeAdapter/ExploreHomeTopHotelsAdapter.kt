package com.example.hbapplicationgroupa.adapter.exploreHomeAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.databinding.ExploreHomeRecyclerviewItem1Binding
import com.example.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseItem

class ExploreHomeTopHotelsAdapter(
    private val topHotelClickListener: TopHotelClickListener
) : RecyclerView.Adapter<ExploreHomeTopHotelsAdapter.Recycler1ViewHolder>() {
    val binding: ExploreHomeRecyclerviewItem1Binding? = null

    private var listOfTopHotels: MutableList<GetTopHotelsResponseItem> = mutableListOf()

    inner class Recycler1ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hotelName: TextView = itemView.findViewById(R.id.exploreHomeFragmentRecyclerViewTextviewName1)
        val hotelPrice: TextView = itemView.findViewById(R.id.exploreHomeFragmentRecyclerViewTextviewPrice1)
        val hotelImage: ImageView = itemView.findViewById(R.id.exploreHomeFragmentRecyclerViewImageview1)

    }

    interface TopHotelClickListener {
        fun onTopHotelClicked(position: Int, hotelId: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Recycler1ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.explore_home_recyclerview_item_1, parent, false)
        return Recycler1ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Recycler1ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(listOfTopHotels[position].thumbnail)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.hotelImage)

        val hotelId = listOfTopHotels[position].id

        holder.hotelName.text = listOfTopHotels[position].name
        holder.hotelPrice.text = listOfTopHotels[position].price

        holder.itemView.setOnClickListener {
            topHotelClickListener.onTopHotelClicked(position, hotelId)
        }
    }

    override fun getItemCount(): Int {
        return listOfTopHotels.size
    }

    fun setListOfTopHotels(topHotels: List<GetTopHotelsResponseItem>) {
        listOfTopHotels.addAll(topHotels)
    }
}