package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.adapter.roomnumber_bottmshit_adapter.RoomNumberBottomSheetAdapter
import com.example.hbapplicationgroupa.model.adaptermodels.RoomNumberBottomSheetData
import com.example.hbapplicationgroupa.databinding.FragmentNumberOfRoomsBottomSheetDialogBinding
import com.example.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseItemRoomTypes
import com.example.hbapplicationgroupa.utils.BookingDetailsOnclickInterface
import com.example.hbapplicationgroupa.viewModel.HotelViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NumberOfRoomsBottomSheetDialogFragment : BottomSheetDialogFragment(), BookingDetailsOnclickInterface {
    private var _binding: FragmentNumberOfRoomsBottomSheetDialogBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: RoomNumberBottomSheetAdapter
    private lateinit var listOfRooms: MutableList<RoomNumberBottomSheetData>
    private val hotelViewModel: HotelViewModel by viewModels()

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

//        hotelViewModel.getRoomTypeFromDb("0dfb4f63-3caf-417e-b7d3-ea63008e8591").observe(viewLifecycleOwner, {
//            it.forEach { data ->
//                val response = data.roomTypes
//
//                adapter.differ.submitList(it)
//            }
//        })

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(R.id.action_numberOfRoomsBottomSheetDialogFragment_to_bookingDetailsFragment)
        }
    }

    override fun passRoomTypes(roomTypes: ArrayList<GetHotelByIdResponseItemRoomTypes>) {

    }
}