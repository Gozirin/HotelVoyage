package com.example.hbapplicationgroupa.ui

import android.annotation.SuppressLint
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
import com.example.hbapplicationgroupa.adapter.allHotelsAdapter.AllHotelsAdapter
import com.example.hbapplicationgroupa.adapter.topHotel.TopHotelAdapter
import com.example.hbapplicationgroupa.database.AuthPreference
import com.example.hbapplicationgroupa.database.dao.WishlistByPageNumberDao
import com.example.hbapplicationgroupa.databinding.FragmentTopHotelsBinding
import com.example.hbapplicationgroupa.model.adaptermodels.Hotel
import com.example.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber.WishlistByPageNumberResponseItems
import com.example.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseItem
import com.example.hbapplicationgroupa.viewModel.HotelViewModel
import com.example.hbapplicationgroupa.viewmodel.CustomerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class TopHotelsFragment : Fragment(),
    TopHotelAdapter.TopHotelsItemClickListener,
    TopHotelAdapter.TopHotelsBookBtnClickListener,
    TopHotelAdapter.TopHotelSaveIconClickListener,
    TopHotelAdapter.TopHotelSaveTextClickListener{

    //Set up view binding here
    private var _binding: FragmentTopHotelsBinding? = null
    private val binding get() = _binding!!

    private lateinit var topHotelAdapter: TopHotelAdapter
    private lateinit var allHotelAdapter: AllHotelsAdapter
    val hotelViewModel : HotelViewModel by viewModels()
    val customerViewModel : CustomerViewModel by viewModels()
    lateinit var recyclerView: RecyclerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTopHotelsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //connecting the view with the data response
        showProgressBar()
        Toast.makeText(requireContext(), "Please, Ensure your internet is active", Toast.LENGTH_LONG).show()
        setupRecyclerView()
        hotelViewModel.fetchTopScreenHotels()
        hotelViewModel.topHotelsLiveData.observe(viewLifecycleOwner,{
            Log.d("Frag -> TopHotel", it.toString())
            topHotelAdapter.setListOfTopHotels(it)
            hideProgressBar()
        })

        // setting back button
        val backButton = binding.topHotelBackBtn
        backButton.setOnClickListener{
            findNavController().popBackStack()
        }

        //setting view button
        binding.topHotelSearchView.setOnSearchClickListener{
//            it.setBackgroundResource(R.color.splash_screen_background_color)
//            Toast.makeText(context, "Searching for Top Luxurious Hotels", Toast.LENGTH_LONG).show()
        }

        onBackPressed()
    }

    override fun topHotelsItemClicked(position: Int) {
//        findNavController().navigate(R.id.action_topHotelsFragment_to_hotelDescription2Fragment)
    }

    override fun topHotelsPreviewBtnClicked(position: Int) {
//        findNavController().navigate(R.id.action_topHotelsFragment_to_bookingDetailsFragment)
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

    @SuppressLint("ResourceAsColor")
    override fun topHotelSaveIconClickListener(position: Int) {

    }

    override fun topHotelSaveTextClickListener(position: Int) {
        AuthPreference.initPreference(requireActivity())
        val authToken = "Bearer ${AuthPreference.getToken(AuthPreference.TOKEN_KEY)}"
        val hotelWish = allHotelAdapter.listOfAllHotels[position]
        hotelWish.id?.let {
            customerViewModel.addWishList(authToken,
                hotelWish,
                it
            )
        }
    }

    //show progress bar
    private fun showProgressBar(){
        binding.topHotelProgressBar.visibility = View.VISIBLE
    }

    //hide progress bar
    private fun hideProgressBar() {
        binding.topHotelProgressBar.visibility = View.INVISIBLE
    }

    //set up recycler view
    private fun setupRecyclerView() {
        topHotelAdapter = TopHotelAdapter(
            requireContext(),
            this,
            this,
            this,
            this)
        binding.topHotelRecyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = topHotelAdapter
        }
    }
}