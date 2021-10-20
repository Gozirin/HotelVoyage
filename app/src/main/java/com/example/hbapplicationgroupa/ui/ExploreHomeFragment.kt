package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.adapter.exploreHomeAdapter.ExploreHomeTopDealsAdapter
import com.example.hbapplicationgroupa.adapter.exploreHomeAdapter.ExploreHomeTopHotelsAdapter
import com.example.hbapplicationgroupa.databinding.FragmentExploreHomeBinding
import com.example.hbapplicationgroupa.viewModel.HotelViewModel
import com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseItem
import com.example.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseItem
import com.example.hbapplicationgroupa.utils.Status

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreHomeFragment : Fragment(), ExploreHomeTopHotelsAdapter.TopHotelClickListener, ExploreHomeTopDealsAdapter.TopDealsClickListener {

    private lateinit var topHotelsAdapter: ExploreHomeTopHotelsAdapter
    private lateinit var topDealsAdapter: ExploreHomeTopDealsAdapter
    private lateinit var topHotelsRecyclerView: RecyclerView
    private lateinit var topDealsRecyclerView: RecyclerView
    private val hotelViewModel: HotelViewModel by viewModels()


    //Set up view binding here
    private var _binding: FragmentExploreHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Enabled view binding here
        _binding = FragmentExploreHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Overriding onBack press to finish activity and exit app
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                requireActivity().finish()
                requireActivity().finishAffinity()
            }
        }

        topHotelsAdapter = ExploreHomeTopHotelsAdapter( this)
        topDealsAdapter = ExploreHomeTopDealsAdapter( this)

        setUpTopHotelsRecyclerView()
        setUpTopDealsRecyclerView()
        getTopHotels()
        getTopDeals()

        requireActivity().onBackPressedDispatcher.addCallback(callback)

        //navigating to topHotel Fragment
        binding.exploreHomeFragmentTopHotelViewAllTv.setOnClickListener {
           findNavController().navigate(R.id.action_exploreHomeFragment_to_topHotelsFragment)
        }
        //click listener for View button navigation to top hotel fragment
        binding.exploreHomeViewAndArrowBtn.setOnClickListener {
            findNavController().navigate(R.id.action_exploreHomeFragment_to_allHotelsFragments)
        }
        //navigation to top Hotel Fragment [it should be topDeal which is yet to be created]
        binding.exploreHomeFragmentTopDealsViewAllTv.setOnClickListener {
           findNavController().navigate(R.id.action_exploreHomeFragment_to_topDealsFragment)
        }
        //click listener for filter button navigation to exploreHomeAfterSearch
        binding.exploreHomeFilterImgBtn.setOnClickListener {
            findNavController().navigate(R.id.action_exploreHomeFragment_to_exploreHomeAfterSearchFragment)
        }
    }


    override fun onTopHotelClicked(position: Int) {
        //Click listener for Top hotel click listeners
        findNavController().navigate(R.id.action_exploreHomeFragment_to_hotelDescription2Fragment)
    }

    override fun topDealsClicked(position: Int) {
        //Click Listener for Top Deal Click Listeners
        findNavController().navigate(R.id.action_exploreHomeFragment_to_hotelDescription2Fragment)
    }

    //set up top hotels recycler view
    private fun setUpTopHotelsRecyclerView() {
        topHotelsRecyclerView = requireView().findViewById(R.id.exploreHomeFragmentRecyclerView1)
        topHotelsRecyclerView.adapter = topHotelsAdapter
        topHotelsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        topHotelsRecyclerView.setHasFixedSize(true)
    }

    //set up top deals recycler view
    private fun setUpTopDealsRecyclerView() {
        topDealsRecyclerView = requireView().findViewById(R.id.exploreHomeFragmentRecyclerView2)
        topDealsRecyclerView.adapter = topDealsAdapter
        topDealsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        topDealsRecyclerView.setHasFixedSize(true)
    }

    //fetch a selected number of top hotels
    private fun getTopHotels(): HotelViewModel {
        binding.exploreHomeFragmentProgressBar1.visibility = View.VISIBLE
        binding.exploreHomeFragmentRecyclerView1.visibility = View.GONE
        hotelViewModel.fetchTopHotels()
        hotelViewModel.exploreHomeTopHotels.observe( viewLifecycleOwner, {
            when (it.statusCode) {
                200 -> {
                    binding.exploreHomeFragmentProgressBar1.visibility = View.GONE
                    it.data.let { topHotels -> renderTopHotelsList(topHotels) }
                    binding.exploreHomeFragmentRecyclerView1.visibility = View.VISIBLE
                    Log.d("ExploreHome 2: ", it.toString())
                }
//                40 -> {
//                    binding.exploreHomeFragmentProgressBar1.visibility = View.VISIBLE
//                    binding.exploreHomeFragmentRecyclerView1.visibility = View.GONE
//                }
                400 -> {
                    //handle error
                    binding.exploreHomeFragmentProgressBar1.visibility = View.GONE
                    Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_LONG).show()
                }
            }
        })
       return hotelViewModel
    }

    //set fetched data to top hotels adapter
    private fun renderTopHotelsList(topHotels: List<GetTopHotelsResponseItem>) {
        topHotelsAdapter.setListOfTopHotels(topHotels)
        topHotelsAdapter.notifyDataSetChanged()
    }

    //fetch a selected number of top deals
    private fun getTopDeals(): HotelViewModel {
        binding.exploreHomeFragmentProgressBar2.visibility = View.VISIBLE
        binding.exploreHomeFragmentRecyclerView2.visibility = View.GONE
        hotelViewModel.fetchTopDeals()
        hotelViewModel.exploreHomeTopDeals.observe(viewLifecycleOwner, {
            when (it.statusCode) {
                200 -> {
                    binding.exploreHomeFragmentProgressBar2.visibility = View.GONE
                    it.data?.let { topDeals -> renderTopDealsList(topDeals) }
                    binding.exploreHomeFragmentRecyclerView2.visibility = View.VISIBLE
                    Log.d("ExploreHome 1: ", it.toString())
                }
//                Status.LOADING -> {
//                    binding.exploreHomeFragmentProgressBar2.visibility = View.VISIBLE
//                    binding.exploreHomeFragmentRecyclerView2.visibility = View.GONE
//                }
                400 -> {
                    binding.exploreHomeFragmentProgressBar2.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
        return  hotelViewModel
    }

    //set fetched data to top deals adapter
    private fun renderTopDealsList(topDeals: List<GetTopDealsResponseItem>) {
        topDealsAdapter.setListOfTopDeals(topDeals)

    }

}