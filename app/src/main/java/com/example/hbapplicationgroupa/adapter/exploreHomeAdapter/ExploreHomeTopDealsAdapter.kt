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
import com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseItem

class ExploreHomeTopDealsAdapter(
    private val topDealsClickListener: TopDealsClickListener
): RecyclerView.Adapter<ExploreHomeTopDealsAdapter.MyViewHolder>() {


    private var listOfTopDealHotels : MutableList<GetTopDealsResponseItem> = mutableListOf()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hotelName2: TextView= itemView.findViewById(R.id.exploreHomeFragmentRecyclerViewTextviewName2)
        val hotelPrice2: TextView = itemView.findViewById(R.id.exploreHomeFragmentRecyclerViewTextviewPrice2)
        val hotelImage2: ImageView = itemView.findViewById(R.id.exploreHomeFragmentRecyclerViewImageview2)
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

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        Glide.with(holder.itemView)
//            .load(listOfTopDealHotels[position].thumbnail)
//            .diskCacheStrategy(DiskCacheStrategy.ALL)
//            .into(holder.hotelImage2)
        holder.hotelName2.text = listOfTopDealHotels[position].name
        holder.hotelPrice2.text = listOfTopDealHotels[position].price.toString()
        holder.itemView.setOnClickListener {
            topDealsClickListener.topDealsClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return listOfTopDealHotels.size
    }

    fun setListOfTopDeals(topDeals: List<GetTopDealsResponseItem>) {
        listOfTopDealHotels.addAll(topDeals)
    }
}