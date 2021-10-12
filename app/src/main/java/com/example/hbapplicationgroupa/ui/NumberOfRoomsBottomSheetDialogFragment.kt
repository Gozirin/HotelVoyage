package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.adapter.roomnumber_bottmshit_adapter.RoomNumberBottomSheetAdapter
import com.example.hbapplicationgroupa.model.adaptermodels.RoomNumberBottomSheetData
import com.example.hbapplicationgroupa.databinding.FragmentNumberOfRoomsBottomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NumberOfRoomsBottomSheetDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentNumberOfRoomsBottomSheetDialogBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: RoomNumberBottomSheetAdapter
    private lateinit var listOfRooms: MutableList<RoomNumberBottomSheetData>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNumberOfRoomsBottomSheetDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listOfRooms = mutableListOf(
            RoomNumberBottomSheetData("Room No. 1", true),
            RoomNumberBottomSheetData("Room No. 2", false),
            RoomNumberBottomSheetData("Room No. 6", false),
            RoomNumberBottomSheetData("Room No. 22", false),
            RoomNumberBottomSheetData("Room No. 24", false)
        )

        adapter = RoomNumberBottomSheetAdapter()
        binding.roomNumberBottomSheetRecyclerView.adapter = adapter
        binding.roomNumberBottomSheetRecyclerView.setHasFixedSize(true)

        adapter.differ.submitList(listOfRooms)

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(R.id.action_numberOfRoomsBottomSheetDialogFragment_to_bookingDetailsFragment)
        }
    }
}