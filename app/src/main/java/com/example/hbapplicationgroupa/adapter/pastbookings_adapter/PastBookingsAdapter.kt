package com.example.hbapplicationgroupa.adapter.pastbookings_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupa.databinding.PastBookingItemsBinding
import com.example.hbapplicationgroupa.model.customermodule.getcustomerbookingbyuserid.BookingByUserIdResponseItems

class PastBookingsAdapter(
    val pastBookingBookingClickListener: PastBookingBookClickListener
    ): PagingDataAdapter<BookingByUserIdResponseItems, PastBookingsAdapter.MyViewHolder>(DiffUtilCallBack()) {

    var bookingList:  List<BookingByUserIdResponseItems> = listOf()

    class MyViewHolder(val binding:PastBookingItemsBinding):RecyclerView.ViewHolder(binding.root){
        val bookBtn = binding.fragmentBookingBookBtn
        var checkInTime: TextView = binding.fragmentBookingTimeTv
        var paymentService: TextView = binding.fragmentBookingHotelTv


        fun bind(booking: BookingByUserIdResponseItems){
            checkInTime.text = booking.checkIn
            paymentService.text = booking.paymentService
        }
    }

    interface PastBookingBookClickListener {
        fun pastBookBtnClicked(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = PastBookingItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(bookingList[position])
        holder.bookBtn.setOnClickListener {
            pastBookingBookingClickListener.pastBookBtnClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return bookingList.size
    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<BookingByUserIdResponseItems>() {
        override fun areItemsTheSame(
            oldItem: BookingByUserIdResponseItems,
            newItem: BookingByUserIdResponseItems
        ): Boolean {
            return oldItem.roomId == newItem.roomId
        }

        override fun areContentsTheSame(
            oldItem: BookingByUserIdResponseItems,
            newItem: BookingByUserIdResponseItems
        ): Boolean {
            return oldItem == newItem
        }

    }


}