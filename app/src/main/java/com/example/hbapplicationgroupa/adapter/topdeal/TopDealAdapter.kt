package com.example.hbapplicationgroupa.adapter.topdeal

import android.annotation.SuppressLint
import android.media.AudioRecord.MetricsConstants.SOURCE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.databinding.TopDealRecyclerviewViewItemBinding
import com.example.hbapplicationgroupa.model.adaptermodels.TopDealModel
import com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseItem

class TopDealAdapter(
    private val topDealItemClickListener: TopDealItemClickListener,
    private val topDealBookBtnClickListener: TopDealBookBtnClickListener
) : RecyclerView.Adapter<TopDealAdapter.TopDealViewHolder>() {

    var topDealList: List<GetTopDealsResponseItem> = listOf()
    val binding: TopDealRecyclerviewViewItemBinding? = null
    inner class TopDealViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val nameOfDeal = view.findViewById<TextView>(R.id.topDeal_recyclerview_hotel_name)
        val priceOfDeal = view.findViewById<TextView>(R.id.topDeal_recyclerview_hotel_price)
        val classOfDeal = view.findViewById<TextView>(R.id.topDeal_recyclerview_hotel_status)
        val ratingOfDeal = view.findViewById<TextView>(R.id.topDeal_recyclerview_hotel_rating)
        val imageOfDeal = view.findViewById<ImageView>(R.id.topDeal_recyclerview_imageview)
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
        holder.priceOfDeal?.text = "$${topDealList[position].price}"
        holder.classOfDeal?.text = topDealList[position].description
        holder.ratingOfDeal?.text = "${topDealList[position].discount}% OFF"
        holder.imageOfDeal?.let {
            Glide.with(it.context)
                .load(topDealList[position].thumbnail)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imageOfDeal)
        };

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
//    fun setHotel(hotel:List<GetTopDealsResponseItem>){
//        this.topDealList = hotel
//        notifyDataSetChanged()
//    }
}