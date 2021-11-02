package com.example.hbapplicationgroupa.adapter.wishlistadapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.database.dao.WishlistByPageNumberDao
import com.example.hbapplicationgroupa.model.adaptermodels.WishListData
import com.example.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber.WishlistByPageNumberResponseItems
import kotlin.math.ceil


class WishListAdapter(var context: Context,
                      private val wishListItemClickListener: WishListItemClickListener,
                      private val wishListBookButtonClickListener: WishListBookButtonClickListener) :
    RecyclerView.Adapter<WishListAdapter.WishListViewHolder>() {

    private var listOfWishList = mutableListOf<WishlistByPageNumberResponseItems>()

    inner class WishListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var hotelPrice: TextView = itemView.findViewById(R.id.tv_hotelprice)
        var hotelName: TextView = itemView.findViewById(R.id.tv_nameOfHotel)
        var hotelRating: TextView = itemView.findViewById(R.id.tv_hotelRating)
        val saveButton: TextView = itemView.findViewById(R.id.tv_Save)
        val bookmarkIcon: ImageView = itemView.findViewById(R.id.bookmarkIcon)
        val bookingBtn: Button = itemView.findViewById(R.id.bookingBtn)
        val savedImage: ImageView = itemView.findViewById(R.id.hotelImageView)

    }

    interface WishListItemClickListener {
        fun wishListClicked(position: Int)
    }

    interface WishListBookButtonClickListener {
        fun wishListBookBtnClicked(position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.wish_list_items, parent, false)
        return WishListViewHolder(view)
    }

    override fun onBindViewHolder(holder: WishListViewHolder, position: Int) {
        holder.hotelName.text = listOfWishList[position].hotelName
        Log.d("Name", listOfWishList[position].hotelName)
        holder.savedImage.let {
            Glide.with(it.context)
                .load(listOfWishList[position].imageUrl)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.savedImage)
        }


        holder.apply {
            // set the onclick listener to the save button
            saveButton.setOnClickListener {

            }

            // set the onclick listener to the bookmark icon
            bookmarkIcon.setOnClickListener {
                bookmarkIcon.setImageResource(R.drawable.bookmark_icon)
            }

            // set the onclick listener to the booking button
            bookingBtn.setOnClickListener {
                wishListBookButtonClickListener.wishListBookBtnClicked(position)
//
            }

            //click listener for each saved Item
            holder.savedImage.setOnClickListener {
                wishListItemClickListener.wishListClicked(position)
            }
        }
    }
    override fun getItemCount() = listOfWishList.size

    //setting data to listOfWishList
    fun setDataToAdapter(list: ArrayList<WishlistByPageNumberResponseItems>){
        listOfWishList.clear()
        listOfWishList = list
        notifyDataSetChanged()
    }
}