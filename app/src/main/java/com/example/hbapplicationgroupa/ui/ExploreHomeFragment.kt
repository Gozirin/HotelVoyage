package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.adapters.ExploreHomeAfterSearchRecyclerViewAdapter1
import com.example.hbapplicationgroupa.adapters.ExploreHomeAfterSearchRecyclerViewAdapter2
import com.example.hbapplicationgroupa.adapters.ExploreHomeRecyclerViewAdapter1
import com.example.hbapplicationgroupa.adapters.ExploreHomeRecyclerViewAdapter2
import com.example.hbapplicationgroupa.databinding.FragmentExploreHomeBinding
import com.example.hbapplicationgroupa.model.Hotel
import com.example.hbapplicationgroupa.model.TopHotel

class ExploreHomeFragment : Fragment() {

    private lateinit var adapter1: ExploreHomeRecyclerViewAdapter1
    private lateinit var adapter2: ExploreHomeRecyclerViewAdapter2
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

    //TODO: UI manipulation can be done here
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewText = binding.exploreHomeFragmentTopHotelTopHotelViewAllTv
        viewText.setOnClickListener {

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

        var listOfHotels = listOf(
            atlantisParadise, burbArab, emiratePalace,
            meridianPalace, thePalms, thePlaza, westinExcelsior
        )

        //instantiate recyclerview to populate it
        adapter1 = ExploreHomeRecyclerViewAdapter1(listOfHotels)
        recyclerView1 = view.findViewById(R.id.exploreHomeFragmentrecyclerView1)

        //populate data into recyclerview
        recyclerView1.adapter = adapter1
        recyclerView1.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView1.setHasFixedSize(false)


        //2nd RecyclerView
        // NOTE: It is using the same data as RecyclerView


        //instantiate recyclerview to populate it
        adapter2 = ExploreHomeRecyclerViewAdapter2(listOfHotels)
        recyclerView2 = view.findViewById(R.id.exploreHomeFragmentRecyclerView2)

        //populate data into recyclerview
        recyclerView2.adapter = adapter2
        recyclerView2.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView2.setHasFixedSize(false)

    }

}