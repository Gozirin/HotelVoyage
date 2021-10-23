package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.adapter.allHotelsAdapter.AllHotelsAdapter
import com.example.hbapplicationgroupa.databinding.FragmentAllHotelsFragmentBinding
import com.example.hbapplicationgroupa.viewModel.HotelViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllHotelsFragments : Fragment(), AllHotelsAdapter.AllHotelsItemClickListener, AllHotelsAdapter.AllHotelsBookBtnClickListener {

    lateinit var allHotelsAdapter: AllHotelsAdapter
    val viewModel: HotelViewModel by viewModels()

    //setting up view binding
    private var _binding: FragmentAllHotelsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllHotelsFragmentBinding.inflate(
            inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Handling on back icon to go back to explore page
        binding.allHotelsBackBtn.setOnClickListener{ findNavController().popBackStack()}

        //setting recyclerview
        setupRecyclerView()

        //showing progress bar while api data is loading or no internet
        showProgressBar("loading hotels, Please, make sure your internet is active")


        //Observing viewModel
        viewModel.fetchAllHotels()
        viewModel.allHotelsLiveData.observe(viewLifecycleOwner, { response ->
            response.pageItems.let {
                Log.d("ObservingVM", response.pageItems.toString())
                if (it != null) {
                    allHotelsAdapter.listOfAllHotels.addAll(it)
                    hideProgressBar()
                    allHotelsAdapter.notifyDataSetChanged()
                }
            }
        })
    }


    override fun allHotelsItemClicked(position: Int) {
        findNavController().navigate(R.id.action_allHotelsFragments_to_hotelDescription2Fragment)
    }

    override fun allHotelsBookBtnClicked(position: Int) {
        findNavController().navigate(R.id.action_allHotelsFragments_to_bookingDetailsFragment)
    }

    private fun hideProgressBar(message: String = "") {
        binding.fragmentAllHotelsProgressBarPb.visibility = View.INVISIBLE
    }

    private fun showProgressBar(message: String = " Please, make sure your Internet is active") {
        binding.fragmentAllHotelsProgressBarPb.visibility = View.VISIBLE
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    //set up recycler view
    private fun setupRecyclerView() {
        allHotelsAdapter = AllHotelsAdapter(this, this)
        binding.allHotelsRecyclerview.apply {
            adapter = allHotelsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}