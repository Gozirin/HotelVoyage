package com.example.hbapplicationgroupa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.model.Hotel

class ExploreHomeTopHotelsAdapter(
    var listOfHotels : List<Hotel>
    ) : RecyclerView.Adapter<ExploreHomeTopHotelsAdapter.Recycler1ViewHolder>() {

    inner class Recycler1ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hotelName = itemView.findViewById<TextView>(R.id.exploreHomeAfterSearchFragmentRecyclerViewTextviewName2)
        val hotelPrice = itemView.findViewById<TextView>(R.id.exploreHomeAfterSearchFragmentRecyclerViewTextviewPrice2)
        val hotelImage = itemView.findViewById<ImageView>(R.id.exploreHomeAfterSearchFragmentRecyclerViewImageview2)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Recycler1ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.explore_home_after_search_recyclerview1_item, parent, false)
        return Recycler1ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: Recycler1ViewHolder,
        position: Int
    ) {
        holder.hotelImage.setImageResource(listOfHotels[position].image)
        holder.hotelName.text = listOfHotels[position].name
        holder.hotelPrice.text = listOfHotels[position].price.toString()
    }

    override fun getItemCount(): Int {
        return listOfHotels.size
    }
}