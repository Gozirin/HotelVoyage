package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.example.hbapplicationgroupa.viewmodelsss.HotelViewModel
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
            findNavController().navigate(R.id.action_exploreHomeFragment_to_topHotelsFragment)
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
        topHotelsRecyclerView = requireView().findViewById(R.id.exploreHomeFragmentrecyclerView1)
        topHotelsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        topHotelsRecyclerView.setHasFixedSize(false)
    }

    //set up top deals recycler view
    private fun setUpTopDealsRecyclerView() {
        topDealsRecyclerView = requireView().findViewById(R.id.exploreHomeFragmentRecyclerView2)
        topDealsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        topDealsRecyclerView.setHasFixedSize(false)
    }

    //fetch a selected number of top hotels
    private fun getTopHotels(): HotelViewModel {
        hotelViewModel.getTopHotels()
        hotelViewModel.topHotels.observe( viewLifecycleOwner, {
            if (it != null) {
                topHotelsAdapter.setListOfTopHotels(it)
                topHotelsRecyclerView.adapter = topHotelsAdapter
            }
        })
       return hotelViewModel
    }

    //fetch a selected number of top deals
    private fun getTopDeals(): HotelViewModel {
        hotelViewModel.getTopDeals()
        hotelViewModel.topDeals.observe(viewLifecycleOwner, {
            if (it != null) {
                topDealsAdapter.setListOfTopDeals(it)
                topDealsRecyclerView.adapter = topDealsAdapter
            }
        })
        return  hotelViewModel
    }

}