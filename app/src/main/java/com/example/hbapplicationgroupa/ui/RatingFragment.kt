package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.adapter.ratingreviewadapter.RatingReviewRecyclerViewAdapter
import com.example.hbapplicationgroupa.databinding.FragmentRatingBinding
import com.example.hbapplicationgroupa.model.adaptermodels.RatingReviewModel
import com.example.hbapplicationgroupa.viewModel.AuthViewModel
import com.example.hbapplicationgroupa.viewmodel.CustomerViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * This Fragment is the Rating page where users can rate hotels
 * It consists of 1 RecyclerView: RatingReviewRecyclerView
 * View id from the xml layouts starts with suffix of; "rating_"
 */
class RatingFragment : Fragment() {
    //Set up view binding here
    private var _binding: FragmentRatingBinding? = null
    private val binding get() = _binding!!

    //Late-initializing RecyclerView Adapter
    lateinit var ratingReviewRecyclerViewAdapter: RatingReviewRecyclerViewAdapter
    lateinit var ratingDataList: ArrayList<RatingReviewModel>

    //getting hotel id from hoteldescription2 fragment
    private val args :RatingFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRatingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ratingReviewRecyclerViewAdapter = RatingReviewRecyclerViewAdapter()

        //Assigning fake data to the RatingRecyclerView Adapter List to test recyclerview (This can be deleted)
        ratingDataList = RatingReviewModel.fakeRatingDataList
        ratingReviewRecyclerViewAdapter.reviewDataList = ratingDataList
        initRatingReviewRecyclerView()
        clickListeners()
        onBackPressed()
    }

    //Method Initializing attributes for RatingReviewRecyclerView
    private fun initRatingReviewRecyclerView(){
        binding.ratingReviewsRecyclerView.apply {
            adapter = ratingReviewRecyclerViewAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun onBackPressed(){
        //Overriding onBack press to navigate to home Fragment onBack Pressed
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }



    //Method Triggering onClickEvents
    private fun clickListeners(){
        binding.ratingPostBtn.setOnClickListener {
            val action = RatingFragmentDirections.actionRatingFragmentToWriteAReviewFragment(args.postReviewHotelId)
            findNavController().navigate(action)
        }

        binding.ratingBackBtn.setOnClickListener {
            findNavController().navigate(R.id.action_ratingFragment_to_hotelDescription2Fragment)
        }
    }
}