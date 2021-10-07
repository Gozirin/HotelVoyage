package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupa.adapter.PastBookingsAdapter
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.databinding.FragmentPastBookingsBinding

class PastBookingsFragment : Fragment(), PastBookingsAdapter.PastBookingBookClickListener {
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
            findNavController().navigate(R.id.action_pastBookingsFragment2_to_profileFragment)
        }

        adapter = PastBookingsAdapter(this)
        binding.bookingRecyclerview.adapter = adapter
        binding.bookingRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        onBackPressed()
    }

    override fun pastBookBtnClicked(position: Int) {
        findNavController().navigate(R.id.action_pastBookingsFragment2_to_bookingDetailsFragment)
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