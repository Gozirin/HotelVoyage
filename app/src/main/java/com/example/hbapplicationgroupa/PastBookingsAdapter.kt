package com.example.hbapplicationgroupa

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupa.databinding.PastBookingItemsBinding

class PastBookingsAdapter(val context: Context): RecyclerView.Adapter<PastBookingsAdapter.MyViewHolder>() {


    class MyViewHolder(val binding:PastBookingItemsBinding):RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = PastBookingItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.fragmentBookingBookBtn.setOnClickListener {
//            holder.binding.fragmentBookingBookBtn.text = "Cancelled"
            Toast.makeText(context, "This cancels the booking", Toast.LENGTH_SHORT).show()

//            holder.binding.fragmentBookingBookBtn.strokeWidth = 0
        }


    }

    override fun getItemCount(): Int {
        return 9
    }
}