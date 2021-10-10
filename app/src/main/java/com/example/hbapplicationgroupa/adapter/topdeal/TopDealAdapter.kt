package com.example.hbapplicationgroupa.adapter.topdeal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.databinding.TopDealRecyclerviewViewItemBinding
import com.example.hbapplicationgroupa.models.dummy_model.TopDealModel

class TopDealAdapter(
    private var topDealList: List<TopDealModel>,
    private val topDealItemClickListener: TopDealItemClickListener,
    private val topDealBookBtnClickListener: TopDealBookBtnClickListener
) : RecyclerView.Adapter<TopDealAdapter.TopDealViewHolder>() {

    val binding: TopDealRecyclerviewViewItemBinding? = null
    inner class TopDealViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val nameOfDeal = binding?.topDealRecyclerviewHotelName
        val priceOfDeal = binding?.topDealRecyclerviewHotelPrice
        val classOfDeal = binding?.topDealRecyclerviewHotelStatus
        val ratingOfDeal = binding?.topDealRecyclerviewHotelRating
        val imageOfDeal = binding?.topDealRecyclerviewImageview
        val hotelBookBtn: AppCompatButton = itemView.findViewById(R.id.topDealBookBtn)
        val topDealView: CardView = itemView.findViewById(R.id.topDealView)
    }

    interface TopDealItemClickListener {
        fun topHotelsItemClicked(position: Int)
    }

    interface TopDealBookBtnClickListener {
        fun topHotelsBookBtnClicked(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopDealViewHolder {
        return TopDealViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.top_deal_recyclerview_view_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TopDealViewHolder, position: Int) {
        holder.nameOfDeal?.text = topDealList[position].name
        holder.priceOfDeal?.text = topDealList[position].price.toString()
        holder.classOfDeal?.text = topDealList[position].classOfHotel
        holder.ratingOfDeal?.text = topDealList[position].rating
        holder.imageOfDeal?.setImageResource(topDealList[position].image)

        holder.topDealView.setOnClickListener {
            topDealItemClickListener.topHotelsItemClicked(position)
        }

        holder.hotelBookBtn.setOnClickListener {
            topDealBookBtnClickListener.topHotelsBookBtnClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return topDealList.size
    }
}