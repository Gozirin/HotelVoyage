package com.example.hbapplicationgroupa.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupa.models.model.RoomNumberBottomSheetData
import com.example.hbapplicationgroupa.databinding.RoomNumberBottomSheetViewHolderBinding

class RoomNumberBottomSheetAdapter : RecyclerView.Adapter<RoomNumberBottomSheetAdapter.RoomNumberBottomSheetViewHolder>() {
    inner class RoomNumberBottomSheetViewHolder(
        var binding: RoomNumberBottomSheetViewHolderBinding): RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object: DiffUtil.ItemCallback<RoomNumberBottomSheetData>(){
        override fun areItemsTheSame(oldItem: RoomNumberBottomSheetData, newItem: RoomNumberBottomSheetData): Boolean {
            return oldItem.roomNumber == newItem.roomNumber
        }

        override fun areContentsTheSame(oldItem: RoomNumberBottomSheetData, newItem: RoomNumberBottomSheetData): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomNumberBottomSheetViewHolder {
        val binding = RoomNumberBottomSheetViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomNumberBottomSheetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomNumberBottomSheetViewHolder, position: Int) {
        with(holder){
            with(differ.currentList[position]){
                binding.roomNumber.text = roomNumber
                binding.radioButton.isChecked = isRadioButtonChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}