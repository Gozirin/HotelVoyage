package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupa.model.Hotel
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.adapter.ExploreHomeAfterSearchRecyclerViewAdapter1
import com.example.hbapplicationgroupa.adapter.ExploreHomeAfterSearchRecyclerViewAdapter2
import com.example.hbapplicationgroupa.databinding.FragmentExploreHomeAfterSearchBinding
import com.example.hbapplicationgroupa.model.TopHotel

class ExploreHomeAfterSearchFragment : Fragment() {

    private lateinit var adapter1: ExploreHomeAfterSearchRecyclerViewAdapter1
    private lateinit var adapter2: ExploreHomeAfterSearchRecyclerViewAdapter2
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerView2: RecyclerView

    //Set up view binding here
    private var _binding: FragmentExploreHomeAfterSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentExploreHomeAfterSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewText = view.findViewById<TextView>(R.id.explore_home_after_search_cardview_view_textview)
        viewText.setOnClickListener {
            //TODO: This should do something
        }

        binding.exploreHomeFragmentAftersearchTopHotelTopHotelViewAllTv.setOnClickListener {
            findNavController().navigate(R.id.action_exploreHomeAfterSearchFragment_to_topHotelsFragment)
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
        adapter1 = ExploreHomeAfterSearchRecyclerViewAdapter1(listOfHotels)
        recyclerView = view.findViewById(R.id.exploreHomeAfterSearchFragmentrecyclerView)

        //populate data into recyclerview
        recyclerView.adapter = adapter1
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.setHasFixedSize(false)


        //dummy data to populate 2nd recyclerView
        //creating dummy Hotel Data
        val hotel1 = TopHotel(
            1, "Atlantis Paradise", 6500, 7500,
            "9 Star Hotel", "99%", R.drawable.hotel_atlantis_paradise_bahamas,
        )
        val hotel2 = TopHotel(
            2, "Burb Arab", 8500, 9500,
            "7 Star Hotel", "100%", R.drawable.hotel_burg_arab_dubai,
        )
        val hotel3 = TopHotel(
            3, "Emirate Palace", 8900, 9800,
            "5 Star Hotel", "100%", R.drawable.hotel_emirates_palace_abu_dhabi,
        )

        val listOfTopHotels = listOf(
            hotel1, hotel2, hotel3
        )

        //instantiate recyclerview to populate it
        adapter2 = ExploreHomeAfterSearchRecyclerViewAdapter2(listOfTopHotels)
        recyclerView2 = view.findViewById(R.id.exploreHomeAfterSearchFragmentRecyclerView2)

        //populate data into recyclerview
        recyclerView2.adapter = adapter2
        recyclerView2.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView2.setHasFixedSize(false)
    }
}