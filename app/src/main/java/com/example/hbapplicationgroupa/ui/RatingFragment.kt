package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupa.adapter.RatingReviewRecyclerViewAdapter
import com.example.hbapplicationgroupa.databinding.FragmentRatingBinding
import com.example.hbapplicationgroupa.model.RatingReviewModel

class RatingFragment : Fragment() {
    //Set up view binding here
    private var _binding: FragmentRatingBinding? = null
    private val binding get() = _binding!!
    //Late-initializing RecyclerView Adapter
    lateinit var ratingReviewRecyclerViewAdapter: RatingReviewRecyclerViewAdapter
    lateinit var ratingDataList: ArrayList<RatingReviewModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Enabled view binding here
        _binding = FragmentRatingBinding.inflate(inflater, container, false)
        return binding.root
    }

    //TODO: UI manipulation can be done here
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ratingReviewRecyclerViewAdapter = RatingReviewRecyclerViewAdapter()

        //Assigning fake data to the RatingRecyclerView Adapter List to test recyclerview (This can be deleted)
        ratingDataList = RatingReviewModel.fakeRatingDataList
        ratingReviewRecyclerViewAdapter.reviewDataList = ratingDataList
        initRatingReviewRecyclerView()
    }

    private fun initRatingReviewRecyclerView(){
        binding.ratingReviewsRecyclerView.apply {
            adapter = ratingReviewRecyclerViewAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}