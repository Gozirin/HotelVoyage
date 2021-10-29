package com.example.hbapplicationgroupa.adapter.roomnumber_bottmshit_adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupa.databinding.RoomNumberBottomSheetViewHolderBinding
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseItemRoomTypes
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelroombyid.GetHotelRoomByIdResponseItem
import com.example.hbapplicationgroupa.utils.RoomTypeAdapterInterface

class RoomNumberBottomSheetAdapter(
    private val roomTypeAdapterInterface: RoomTypeAdapterInterface
): RecyclerView.Adapter<RoomNumberBottomSheetAdapter.RoomNumberBottomSheetViewHolder>() {

    inner class RoomNumberBottomSheetViewHolder(
        var binding: RoomNumberBottomSheetViewHolderBinding
    ): RecyclerView.ViewHolder(binding.root)

//    var listOfRoomsByNumber = arrayListOf<GetHotelRoomByIdResponseItem>()

    var listOfRoomsByName = arrayListOf<GetHotelByIdResponseItemRoomTypes>()


//    fun addRoomNumber(roomNumber: ArrayList<GetHotelRoomByIdResponseItem>){
//        listOfRoomsByNumber.addAll(roomNumber)
//    }

    fun addRoomType(roomName: ArrayList<GetHotelByIdResponseItemRoomTypes>){
        listOfRoomsByName.addAll(roomName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomNumberBottomSheetViewHolder {
        val binding = RoomNumberBottomSheetViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomNumberBottomSheetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomNumberBottomSheetViewHolder, position: Int) {
        with(holder){
//            with(listOfRoomsByNumber[position]){
//                binding.roomNumber.text = roomNo.toString()

                with(listOfRoomsByName[position]) {
                    binding.roomName.text = name
                }
              //  binding.roomTypeNumberCount.text = numberOfRooms

                val roomTypeList = arrayListOf<String>()
//                binding.roomTypeCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
//                    if (isChecked){
//                        roomTypeList.add(binding.roomType.text.toString())
//                        Log.d("GKB", "$roomTypeList")
//                    }
//                }

                //Get selection from adapter and send it to rooms bottom sheet fragment
                binding.roomTypeCheckbox.setOnClickListener {
                    if (binding.roomTypeCheckbox.isChecked) {
                        roomTypeList.add(binding.roomName.text.toString())
                        Log.d("GKB", "$roomTypeList")
                    }else{
                      //  roomTypeList.remove(binding.roomType.text.toString())
                    }

                    val data = roomTypeList.joinToString(", ")
                    roomTypeAdapterInterface.getSelectedRoomTypes(position, data)
                }


//                    var roomCount = 0
//                    binding.roomTypeMinusButton.setOnClickListener {
//                        if (roomCount > 0) {
//                            roomCount--
//                            binding.roomTypeNumberCount.text = roomCount.toString()
//                        }
//                    }
//
//                    binding.roomTypeAddButton.setOnClickListener {
//                        roomCount++
//                        binding.roomTypeNumberCount.text = roomCount.toString()
//                    }
//
//                    var numberOfRoomsData = ""
//                    if (roomCount == 1) {
//                        numberOfRoomsData = "$roomCount $name suite"
//                    } else if (roomCount > 1) {
//                        numberOfRoomsData = "$roomCount $name suites"
//                    }
//
//                    val numberOfRoomsDataToString = numberOfRoomsData.toString()

            }
        }

    override fun getItemCount() = listOfRoomsByName.size
}