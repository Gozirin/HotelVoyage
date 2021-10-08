package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.adapter.exploreHomeAdapter.ExploreHomeTopDealsAdapter
import com.example.hbapplicationgroupa.adapter.exploreHomeAdapter.ExploreHomeTopHotelsAdapter
import com.example.hbapplicationgroupa.databinding.FragmentExploreHomeBinding
import com.example.hbapplicationgroupa.models.dummy_model.Hotel

class ExploreHomeFragment : Fragment(), ExploreHomeTopHotelsAdapter.TopHotelClickListener, ExploreHomeTopDealsAdapter.TopDealsClickListener {

    private lateinit var adapter1: ExploreHomeTopHotelsAdapter
    private lateinit var adapter2: ExploreHomeTopDealsAdapter
    private lateinit var recyclerView1: RecyclerView
    private lateinit var recyclerView2: RecyclerView


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
        requireActivity().onBackPressedDispatcher.addCallback(callback)
        findNavController().popBackStack(R.id.action_exploreHomeFragment_to_splashScreenFragment, true)

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
           findNavController().navigate(R.id.action_exploreHomeFragment_to_topHotelsFragment)
        }
        //click listener for filter button navigation to exploreHomeAfterSearch
        binding.exploreHomeFilterImgBtn.setOnClickListener {
            findNavController().navigate(R.id.action_exploreHomeFragment_to_exploreHomeAfterSearchFragment)
        }


        //creating dummy Hotel Data
        val atlantisParadise = Hotel(
            1, "Atlantis Paradise", 6500,
            "9 Star Hotel", "99%", R.drawable.hotel_atlantis_paradise_bahamas
        )
        val burbArab = Hotel(
            2, "Burb Arab", 8500,
            "7 Star Hotel", "100%", R.drawable.hotel_burg_arab_dubai
        )
        val emiratePalace = Hotel(
            3, "Emirate Palace", 8900,
            "5 Star Hotel", "100%", R.drawable.hotel_emirates_palace_abu_dhabi
        )
        val meridianPalace = Hotel(
            4, "Meridian Palace", 5500,
            "9 Star Hotel", "98%", R.drawable.hotel_merdan_palace_turkey
        )
        val thePalms = Hotel(
            5, "The Palms", 6500,
            "6 Star Hotel", "99%", R.drawable.hotel_the_palms_las_vegas
        )
        val thePlaza = Hotel(
            6, "The Plaza", 7800,
            "9 Star Hotel", "100%", R.drawable.hotel_the_plaza_newyork
        )
        val westinExcelsior = Hotel(
            7, "Westin Excelsior", 9500,
            "12 Star Hotel", "100%", R.drawable.hotel_westin_excelsior_rome
        )

        val listOfHotels = listOf(
            atlantisParadise, burbArab, emiratePalace,
            meridianPalace, thePalms, thePlaza, westinExcelsior
        )

        //instantiate recyclerview to populate it
        adapter1 = ExploreHomeTopHotelsAdapter(listOfHotels, this)
        recyclerView1 = view.findViewById(R.id.exploreHomeFragmentrecyclerView1)

        //populate data into recyclerview
        recyclerView1.adapter = adapter1
        recyclerView1.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView1.setHasFixedSize(false)


        //2nd RecyclerView
        // NOTE: It is using the same data as RecyclerView
        val listOfTopDealHotels = listOf(
            atlantisParadise, burbArab, emiratePalace,
            meridianPalace, thePalms, thePlaza, westinExcelsior
        )

        //instantiate recyclerview to populate it
        adapter2 = ExploreHomeTopDealsAdapter(listOfTopDealHotels, this)
        recyclerView2 = view.findViewById(R.id.exploreHomeFragmentRecyclerView2)

        //populate data into recyclerview
        recyclerView2.adapter = adapter2
        recyclerView2.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView2.setHasFixedSize(false)

    }


    override fun onTopHotelClicked(position: Int) {
        //Click listener for Top hotel click listeners
        findNavController().navigate(R.id.action_exploreHomeFragment_to_hotelDescription2Fragment)
    }

    override fun topDealsClicked(position: Int) {
        //Click Listener for Top Deal Click Listeners
        findNavController().navigate(R.id.action_exploreHomeFragment_to_hotelDescription2Fragment)
    }
}