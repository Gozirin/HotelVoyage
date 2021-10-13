package com.example.hbapplicationgroupa.adapter.exploreHomeAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.databinding.ExploreHomeRecyclerviewItem2Binding
import com.example.hbapplicationgroupa.model.adaptermodels.Hotel

class ExploreHomeTopDealsAdapter(
    private var listOfTopDealHotels : List<Hotel>,
    private val topDealsClickListener: TopDealsClickListener
): RecyclerView.Adapter<ExploreHomeTopDealsAdapter.MyViewHolder>() {
    val binding : ExploreHomeRecyclerviewItem2Binding? = null

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hotelName2 = binding?.exploreHomeFragmentRecyclerViewTextviewName2
        val hotelPrice2 = binding?.exploreHomeFragmentRecyclerViewTextviewPrice2
        val hotelImage2 = binding?.exploreHomeFragmentRecyclerViewImageview2
    }

    interface TopDealsClickListener {
        fun topDealsClicked(position: Int)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.explore_home_recyclerview_item_2, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        holder.hotelImage2?.setImageResource(listOfTopDealHotels[position].image)
        holder.hotelName2?.text = listOfTopDealHotels[position].name
        holder.hotelPrice2?.text = listOfTopDealHotels[position].price.toString()
        holder.itemView.setOnClickListener {
            topDealsClickListener.topDealsClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return listOfTopDealHotels.size
    }


}