package com.example.hbapplicationgroupa.adapter.exploreHomeAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.databinding.ExploreHomeRecyclerviewItem1Binding
import com.example.hbapplicationgroupa.databinding.ExploreHomeRecyclerviewItem2Binding
import com.example.hbapplicationgroupa.model.Hotel

class ExploreHomeTopHotelsAdapter(
    var listOfTopHotels : List<Hotel>
    ) : RecyclerView.Adapter<ExploreHomeTopHotelsAdapter.Recycler1ViewHolder>() {
    val binding: ExploreHomeRecyclerviewItem1Binding? = null

    inner class Recycler1ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hotelName = binding?.exploreHomeFragmentRecyclerViewTextviewName1
        val hotelPrice = binding?.exploreHomeFragmentRecyclerViewTextviewPrice1
        val hotelImage = binding?.exploreHomeFragmentRecyclerViewImageview1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Recycler1ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.explore_home_recyclerview_item_1, parent, false)
        return Recycler1ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Recycler1ViewHolder, position: Int) {
        holder.hotelImage?.setImageResource(listOfTopHotels[position].image)
        holder.hotelName?.text = listOfTopHotels[position].name
        holder.hotelPrice?.text = listOfTopHotels[position].price.toString()
    }

    override fun getItemCount(): Int {
        return listOfTopHotels.size
    }
}