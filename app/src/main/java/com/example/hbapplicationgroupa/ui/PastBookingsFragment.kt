package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isEmpty
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
        //val authToken = "Bearer ${AuthPreference.getToken(AuthPreference.TOKEN_KEY)}"

        observeBookingHistoryFlow()
       // getUserBooking(1, 5, authToken)//
        displayNoBookingImage()
        onBackPressed()
    }

    private fun displayNoBookingImage(){
        if (adapter.itemCount == 0){
            binding.noBooking.visibility = View.VISIBLE
            binding.noBookingTxt.visibility = View.VISIBLE
        }
    }

    override fun pastBookBtnClicked(position: Int) {
        findNavController().navigate(R.id.action_pastBookingsFragment2_to_bookingDetailsFragment)
    }

//    private fun getUserBooking(pageNumber: Int,
//                               pageSize: Int,
//                               authToken: String){
//        viewModel.getUserBooking(pageNumber, pageSize, authToken)
//    }

    private fun observeBookingHistoryFlow(){
        viewModel = ViewModelProvider(this)[CustomerViewModel::class.java]

//        viewModel.getUserBookingLiveData.observe(viewLifecycleOwner, Observer {
//            if (it != null){
//                adapter.bookingList = it.data.pageItems
//                adapter.notifyDataSetChanged()
//                Log.d("PastBooking 1:", it.toString())
//            }
//        })
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.bookingHistory.collectLatest {

                adapter.submitData(it)
                adapter.notifyDataSetChanged()
                Log.d("BookingHistory 2", "${adapter.itemCount}")
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