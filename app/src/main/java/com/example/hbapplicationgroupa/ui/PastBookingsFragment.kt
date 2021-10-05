package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupa.adapter.PastBookingsAdapter
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.databinding.FragmentPastBookingsBinding

class PastBookingsFragment : Fragment() {
    private var _binding: FragmentPastBookingsBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter: PastBookingsAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPastBookingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentBookingBackIv.setOnClickListener {
            findNavController().navigate(R.id.action_pastBookingsFragment2_to_profileFragment)
        }

        //TODO: Set click event on recycler view item
        binding.fragmentBookingBackIv.setOnClickListener {
            findNavController().navigate(R.id.action_pastBookingsFragment2_to_bookingDetailsFragment)
        }

        adapter = PastBookingsAdapter(requireContext())
        binding.bookingRecyclerview.adapter = adapter
        binding.bookingRecyclerview.layoutManager = LinearLayoutManager(requireContext())
    }
}