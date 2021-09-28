package com.example.hbapplicationgroupa

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupa.databinding.PastBookingItemsBinding

class PastBookingsAdapter: RecyclerView.Adapter<PastBookingsAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: PastBookingItemsBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = PastBookingItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.fragmentBookingBookBtn.text = "Book Again"

    }

    override fun getItemCount(): Int {
        return 9
    }
}