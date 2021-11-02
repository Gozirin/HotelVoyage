package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isEmpty
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.filter
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupa.adapter.pastbookings_adapter.PastBookingsAdapter
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.database.AuthPreference
import com.example.hbapplicationgroupa.databinding.FragmentPastBookingsBinding
import com.example.hbapplicationgroupa.viewmodel.CustomerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PastBookingsFragment : Fragment(), PastBookingsAdapter.PastBookingBookClickListener {
    private var _binding: FragmentPastBookingsBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter: PastBookingsAdapter
    lateinit var viewModel: CustomerViewModel


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

        AuthPreference.initPreference(requireActivity())
        binding.pastBookingProgressBar.visibility = View.VISIBLE
        observeBookingHistoryFlow()
        binding.pastBookingProgressBar.visibility = View.GONE
        displayNoBookingImage()
        onBackPressed()
    }

    private fun displayNoBookingImage(){
        if (binding.bookingRecyclerview.isVisible){
            binding.noBookingTxt.text = ""
        }else{
            binding.noBookingTxt.text = "You have made no bookings"
        }
    }

    override fun pastBookBtnClicked(position: Int) {
        //hotelId and roomItem needs to be passed here
//        val action = PastBookingsFragmentDirections.actionPastBookingsFragment2ToBookingDetailsFragment("", adapter.)
//        findNavController().navigate(action)
    }

    private fun observeBookingHistoryFlow(){
        viewModel = ViewModelProvider(this)[CustomerViewModel::class.java]
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.bookingHistory.collectLatest {
                adapter.submitData(it)
            }
        }
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