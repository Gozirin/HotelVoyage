package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.adapter.topHotel.TopHotelAdapter
import com.example.hbapplicationgroupa.databinding.FragmentTopHotelsBinding
import com.example.hbapplicationgroupa.model.adaptermodels.Hotel

class TopHotelsFragment : Fragment(), TopHotelAdapter.TopHotelsItemClickListener, TopHotelAdapter.TopHotelsBookBtnClickListener {
    private lateinit var adapter: TopHotelAdapter

    //Set up view binding here
    private var _binding: FragmentTopHotelsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTopHotelsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topHotelFilterIcon.setOnClickListener {
            findNavController().navigate(R.id.action_topHotelsFragment_to_bookingDetailsFragment)
        }

        binding.topHotelBackBtn.setOnClickListener {
            findNavController().navigate(R.id.action_topHotelsFragment_to_exploreHomeAfterSearchFragment)
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
        adapter = TopHotelAdapter(listOfHotels, this, this)
        val recyclerView = binding.topHotelRecyclerview
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(false)

        // setting back button
        val backButton = binding.topHotelBackBtn
        backButton.setOnClickListener{
            findNavController().popBackStack()
        }
        //setting view button
        binding.topHotelSearchView.setOnSearchClickListener{
            it.setBackgroundResource(R.color.splash_screen_background_color)
            Toast.makeText(context, "Searching for Top Luxurious Hotels", Toast.LENGTH_LONG).show()
        }

        onBackPressed()

        //filter button
//        binding.topHotelFilter.setOnClickListener{
//            it.setBackgroundResource(R.color.purple_500)
//            Toast.makeText(context, "Filter icon is clicked", Toast.LENGTH_LONG).show()
//        }

//        val saveButton = view.findViewById<ImageButton>(R.id.topHotel_recyclerview_imageview_save)
//        saveButton.setOnClickListener{
//            it.setBackgroundResource(R.color.purple_700)
//            Toast.makeText(context, "Hotel Saved", Toast.LENGTH_SHORT).show()
//        }

        //setting book hotel
//        val bookHotelBtn = view.findViewById<Button>(R.id.topHotel_recyclerview_book_now)
//        bookHotelBtn.setOnClickListener{
//            it.setBackgroundResource(R.color.purple_500)
//            Toast.makeText(requireContext(), "Hotel Booked", Toast.LENGTH_SHORT).show()
//        }
    }

    override fun topHotelsItemClicked(position: Int) {
        findNavController().navigate(R.id.action_topHotelsFragment_to_hotelDescription2Fragment)
        Toast.makeText(requireContext(), "clicked", Toast.LENGTH_SHORT).show()
    }

    override fun topHotelsBookBtnClicked(position: Int) {
        findNavController().navigate(R.id.action_topHotelsFragment_to_bookingDetailsFragment)
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