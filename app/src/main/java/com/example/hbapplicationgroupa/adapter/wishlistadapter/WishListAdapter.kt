package com.example.hbapplicationgroupa.adapter.wishlistadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.model.adaptermodels.WishListData


class WishListAdapter(val wishListitems: ArrayList<WishListData>, var context: Context,
                      private val wishListItemClickListener: WishListItemClickListener,
                        private val wishListBookButtonClickListener: WishListBookButtonClickListener) :
    RecyclerView.Adapter<WishListAdapter.WishListViewHolder>() {


    class WishListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var hotelPrice: TextView = itemView.findViewById(R.id.hotelprice)
        var hotelName: TextView = itemView.findViewById(R.id.nameOfHotel)
        var hotelRating: TextView = itemView.findViewById(R.id.hotelRating)
        val saveButton: TextView = itemView.findViewById(R.id.txtSave)
        val bookmarkIcon: ImageView = itemView.findViewById(R.id.bookmarkIcon)
        val bookingBtn: Button = itemView.findViewById(R.id.bookingBtn)
        val savedImage: ImageView = itemView.findViewById(R.id.hotelImageView)

        fun bind(itemList: WishListData) {
            hotelName.text = itemList.hotelName
            hotelPrice.text = itemList.hotelprice
            hotelRating.text = itemList.hotelRating
        }
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
        var curentItem = wishListitems[position]
        holder.bind(curentItem)

        holder.apply {
            // set the onclick listener to the save button
            saveButton.setOnClickListener {

                //remove the toast and add navigation to required destination
                Toast.makeText(context, "saved to wishlist", Toast.LENGTH_LONG).show()
            }

            // set the onclick listener to the bookmark icon
            bookmarkIcon.setOnClickListener {

                //remove the toast and add navigation to required destination
                bookmarkIcon.setImageResource(R.drawable.bookmark_icon)
                Toast.makeText(context, "this hotel has been add to your list", Toast.LENGTH_LONG)
                    .show()
            }

            // set the onclick listener to the booking button
            bookingBtn.setOnClickListener {

                //remove the toast and add navigation to required destination
                wishListBookButtonClickListener.wishListBookBtnClicked(position)
//                Toast.makeText(
//                    context,
//                    " you will be redirected to the booking page, Thank you",
//                    Toast.LENGTH_LONG
//                ).show()
            }

            //click listener for each saved Item
            holder.savedImage.setOnClickListener {
                wishListItemClickListener.wishListClicked(position)
            }
        }
    }
    override fun getItemCount() = wishListitems.size

}