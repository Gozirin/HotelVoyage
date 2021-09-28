package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupa.PastBookingsAdapter
import com.example.hbapplicationgroupa.databinding.FragmentPastBookingsBinding

class PastBookingsFragment : Fragment() {
    //Set up view binding here
    private var _binding: FragmentPastBookingsBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter: PastBookingsAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Enabled view binding here
        _binding = FragmentPastBookingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    //TODO: UI manipulation can be done here
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PastBookingsAdapter()
        binding.bookingRecyclerview.adapter = adapter
        binding.bookingRecyclerview.layoutManager = LinearLayoutManager(requireContext())
    }
}