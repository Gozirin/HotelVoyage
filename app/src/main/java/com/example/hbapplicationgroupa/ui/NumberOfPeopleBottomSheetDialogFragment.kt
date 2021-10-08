package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupa.*
import com.example.hbapplicationgroupa.adapter.peoplenumber_bottomshit_adapter.PeopleNumberBottomSheetAdapter
import com.example.hbapplicationgroupa.databinding.FragmentNumberOfPeopleBottomSheetDialogBinding
import com.example.hbapplicationgroupa.models.dummy_model.PeopleNumberBottomSheetData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NumberOfPeopleBottomSheetDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentNumberOfPeopleBottomSheetDialogBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: PeopleNumberBottomSheetAdapter
    private lateinit var listOfPeople: MutableList<PeopleNumberBottomSheetData>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNumberOfPeopleBottomSheetDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listOfPeople = mutableListOf(
            PeopleNumberBottomSheetData("Adults", "16+ years", "1"),
            PeopleNumberBottomSheetData("Teens", "12-15 years", "0"),
            PeopleNumberBottomSheetData("Children", "2-11 years", "0"),
            PeopleNumberBottomSheetData("Infants", "under 2 years", "0")
        )

        adapter = PeopleNumberBottomSheetAdapter()
        binding.peopleNumberBottomSheetRecyclerView.adapter = adapter
        binding.peopleNumberBottomSheetRecyclerView.setHasFixedSize(true)

        adapter.differ.submitList(listOfPeople)

        Log.d("GKB", "NUMBER OF PEOPLE: ${adapter.differ.currentList}")

        binding.cancelButton.setOnClickListener {
            Toast.makeText(context, "Button clicked", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_numberOfPeopleBottomSheetDialogFragment_to_bookingDetailsFragment)
        }
    }
}