package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hbapplicationgroupa.adapter.roomnumber_bottmshit_adapter.RoomNumberBottomSheetAdapter
import com.example.hbapplicationgroupa.databinding.FragmentNumberOfRoomsBottomSheetDialogBinding
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseItemRoomTypes
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseModel
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelroombyid.GetHotelRoomByIdResponseItem
import com.example.hbapplicationgroupa.utils.RoomTypeAdapterInterface
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NumberOfRoomsBottomSheetDialogFragment(
    private val roomTypes: ArrayList<GetHotelByIdResponseItemRoomTypes>,
    private val roomTypeAdapterInterface: RoomTypeAdapterInterface
) : BottomSheetDialogFragment(), RoomTypeAdapterInterface {
    private var _binding: FragmentNumberOfRoomsBottomSheetDialogBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: RoomNumberBottomSheetAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNumberOfRoomsBottomSheetDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RoomNumberBottomSheetAdapter(this)
        binding.roomNumberBottomSheetRecyclerView.adapter = adapter
        binding.roomNumberBottomSheetRecyclerView.setHasFixedSize(true)

        adapter.addRoomType(roomTypes)

        binding.cancelButton.setOnClickListener {
            dismiss()
        }
    }

    override fun getSelectedRoomTypes(position: Int, name: String) {
        binding.doneButton.setOnClickListener {
            roomTypeAdapterInterface.getSelectedRoomTypes(position, name)
            dismiss()

        }
        Log.d("GKB", "$name was selected")
    }
}