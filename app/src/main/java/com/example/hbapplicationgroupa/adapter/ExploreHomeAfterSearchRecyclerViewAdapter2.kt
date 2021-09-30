package com.example.hbapplicationgroupa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.model.TopHotel

class ExploreHomeAfterSearchRecyclerViewAdapter2(
    private var listOfTopHotels : List<TopHotel>
): RecyclerView.Adapter<ExploreHomeAfterSearchRecyclerViewAdapter2.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hotelName2 = itemView.findViewById<TextView>(R.id.exploreHomeAfterSearchFragmentRecyclerViewTextviewName2)
        val hotelPrice2 = itemView.findViewById<TextView>(R.id.exploreHomeAfterSearchFragmentRecyclerViewTextviewPrice2)
        val hotelImage2 = itemView.findViewById<ImageView>(R.id.exploreHomeAfterSearchFragmentRecyclerViewImageview2)
        val hotelCancelledPrice = itemView.findViewById<TextView>(R.id.exploreHomeAfterSearchFragmentRecyclerViewCancelledPrice2)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.explore_home_after_search_recyclerview_item_2, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        holder.hotelImage2.setImageResource(listOfTopHotels[position].image)
        holder.hotelName2.text = listOfTopHotels[position].name
        holder.hotelPrice2.text = listOfTopHotels[position].price.toString()
        holder.hotelCancelledPrice.text = listOfTopHotels[position].cancelledPrice.toString()

    }

    override fun getItemCount(): Int {
        return listOfTopHotels.size
    }


}