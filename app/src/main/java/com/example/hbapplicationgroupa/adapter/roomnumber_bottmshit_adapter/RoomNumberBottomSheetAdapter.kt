package com.example.hbapplicationgroupa.adapter.roomnumber_bottmshit_adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupa.databinding.RoomNumberBottomSheetViewHolderBinding
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseItemRoomTypes
import com.example.hbapplicationgroupa.utils.RoomTypeAdapterInterface

class RoomNumberBottomSheetAdapter(
    private val roomTypeAdapterInterface: RoomTypeAdapterInterface
): RecyclerView.Adapter<RoomNumberBottomSheetAdapter.RoomNumberBottomSheetViewHolder>() {

    inner class RoomNumberBottomSheetViewHolder(
        var binding: RoomNumberBottomSheetViewHolderBinding
    ): RecyclerView.ViewHolder(binding.root)

    var listOfRooms = arrayListOf<GetHotelByIdResponseItemRoomTypes>()

    fun addRoomType(roomType: ArrayList<GetHotelByIdResponseItemRoomTypes>){
        listOfRooms.addAll(roomType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomNumberBottomSheetViewHolder {
        val binding = RoomNumberBottomSheetViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomNumberBottomSheetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomNumberBottomSheetViewHolder, position: Int) {
        with(holder){
            with(listOfRooms[position]){
                binding.radioButton.text = name

                binding.radioButton.setOnClickListener {
                    if (!binding.radioButton.isSelected){
                        binding.radioButton.isChecked = true
                        binding.radioButton.isSelected = true
                    }else{
                        binding.radioButton.isChecked = false
                        binding.radioButton.isSelected = false
                    }
                }
            }
        }
    }

    override fun getItemCount() = listOfRooms.size
}