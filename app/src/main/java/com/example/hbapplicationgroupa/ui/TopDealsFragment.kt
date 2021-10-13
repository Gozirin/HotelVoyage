package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.adapter.topdeal.TopDealAdapter
import com.example.hbapplicationgroupa.databinding.FragmentTopDealsBinding
import com.example.hbapplicationgroupa.model.adaptermodels.TopDealModel

class TopDealsFragment : Fragment(), TopDealAdapter.TopDealItemClickListener, TopDealAdapter.TopDealBookBtnClickListener {

    private lateinit var dummyTopDealList: List<TopDealModel>
    private lateinit var topDealAdapter: TopDealAdapter

    //Set up view binding here
    private var _binding: FragmentTopDealsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTopDealsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dummyTopDealList = TopDealModel.topDealList
        topDealAdapter = TopDealAdapter(dummyTopDealList, this, this)

        binding.topDealRecyclerview.apply {
            adapter = topDealAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.topDealBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        onBackPressed()
    }

    override fun topHotelsItemClicked(position: Int) {
        findNavController().navigate(R.id.action_topDealsFragment_to_hotelDescription2Fragment)
    }

    override fun topHotelsBookBtnClicked(position: Int) {
        findNavController().navigate(R.id.action_topDealsFragment_to_bookingDetailsFragment)
    }

    //Method to handle back press
    private fun onBackPressed(){
        //Overriding onBack press to navigate to home Fragment onBack Pressed
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

}