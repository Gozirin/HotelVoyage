package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupa.*
import com.example.hbapplicationgroupa.adapter.peoplenumber_bottomshit_adapter.PeopleNumberBottomSheetAdapter
import com.example.hbapplicationgroupa.databinding.FragmentNumberOfPeopleBottomSheetDialogBinding
import com.example.hbapplicationgroupa.model.adaptermodels.PeopleNumberBottomSheetData
import com.example.hbapplicationgroupa.utils.PeopleBottomSheetOnClickInterface
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlin.properties.Delegates

class NumberOfPeopleBottomSheetDialogFragment(
    private val onClickInterface: PeopleBottomSheetOnClickInterface
    ) : BottomSheetDialogFragment() {
    private var _binding: FragmentNumberOfPeopleBottomSheetDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNumberOfPeopleBottomSheetDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        var adultCount = 0
        binding.adultAddButton.setOnClickListener {
            adultCount++
            binding.adultNumberCount.text = adultCount.toString()
        }

        binding.adultMinusButton.setOnClickListener {
            if (adultCount > 0){
                adultCount--
                binding.adultNumberCount.text = adultCount.toString()
            }
        }

        var teensCount = 0
        binding.teenAddButton.setOnClickListener {
            teensCount++
            binding.teenNumberCount.text = teensCount.toString()
        }

        binding.teenMinusButton.setOnClickListener {
            if (teensCount > 0){
                teensCount--
                binding.teenNumberCount.text = teensCount.toString()
            }
        }

        var childrenCount = 0
        binding.childrenAddButton.setOnClickListener {
            childrenCount++
            binding.childrenNumberCount.text = childrenCount.toString()
        }

        binding.childrenMinusButton.setOnClickListener {
            if (childrenCount > 0){
                childrenCount--
                binding.childrenNumberCount.text = childrenCount.toString()
            }
        }

        var infantsCount = 0
        binding.infantsAddButton.setOnClickListener {
            infantsCount++
            binding.infantsNumberCount.text = infantsCount.toString()
        }

        binding.infantsMinusButton.setOnClickListener {
            if (infantsCount > 0){
                infantsCount--
                binding.infantsNumberCount.text = infantsCount.toString()
            }
        }

        binding.doneButton.setOnClickListener {
            val peopleData = mutableListOf<String>()
            var adultsData = ""
            var teensData = ""
            var childrenData = ""
            var infantsData = ""

            if (adultCount == 1){
                adultsData = "$adultCount adult"
            }else if (adultCount > 1){
                adultsData = "$adultCount adults"
            }

            if (teensCount == 1){
                teensData = "$teensCount teen"
            }else if(teensCount > 1){
                teensData = "$teensCount teens"
            }

            if (childrenCount == 1){
                childrenData = "$childrenCount child"
            }else if (childrenCount > 1){
                childrenData = "$childrenCount children"
            }

            if (infantsCount == 1){
                infantsData = "$infantsCount infant"
            }else if(infantsCount > 1){
                infantsData = "$infantsCount infants"
            }

            if (adultCount > 0){
                peopleData.add(adultsData)
            }

            if (teensCount > 0){
                peopleData.add(teensData)
            }

            if (childrenCount > 0){
                peopleData.add(childrenData)
            }

            if (infantsCount > 0){
                peopleData.add(infantsData)
            }

            val peopleDataToString = peopleData.joinToString(", ")
            onClickInterface.passData(peopleDataToString)
            dismiss()
        }
    }
}