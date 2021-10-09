package com.example.hbapplicationgroupa.adapter.pastbookings_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupa.databinding.PastBookingItemsBinding

class PastBookingsAdapter(val pastBookingBookingClickListener: PastBookingBookClickListener): RecyclerView.Adapter<PastBookingsAdapter.MyViewHolder>() {


    class MyViewHolder(val binding:PastBookingItemsBinding):RecyclerView.ViewHolder(binding.root){
        val bookBtn = binding.fragmentBookingBookBtn
    }

    interface PastBookingBookClickListener {
        fun pastBookBtnClicked(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = PastBookingItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bookBtn.setOnClickListener {
            pastBookingBookingClickListener.pastBookBtnClicked(position)
        }


    }

    override fun getItemCount(): Int {
        return 9
    }
}