package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.databinding.FragmentWriteAReviewBinding
import com.example.hbapplicationgroupa.viewmodel.CustomerViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 * This Fragment is where users write reviews and post
 * View id in the Fragment start with suffix of; "review_"
 */
@AndroidEntryPoint
class WriteAReviewFragment : Fragment() {
    private var _binding: FragmentWriteAReviewBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CustomerViewModel by viewModels()

    private lateinit var postReviewBtn: Button


    //getting hotel id from rating fragment
    private val args:WriteAReviewFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWriteAReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.reviewBackBtn.setOnClickListener {
            findNavController().navigate(R.id.action_writeAReviewFragment_to_ratingFragment)
        }


//        binding.reviewPostBtn.setOnClickListener {
//            findNavController().navigate(R.id.action_writeAReviewFragment_to_ratingFragment)
//        }

        //adding review
        postReviewBtn = binding.reviewPostBtn
        val review = binding.reviewEdt.text.toString().trim()
        postReviewBtn.setOnClickListener {
            addReview(review, args.postReviewHotelId)
        }
        observeAddReviewLiveData()

        onBackPressed()
    }

    //network call to add review
    private fun addReview(comment:String, hotelId:String){
        viewModel.addReview(comment, hotelId)
        binding.fragmentRatingProgressBarPb.visibility = View.VISIBLE
    }

    private fun observeAddReviewLiveData(){
        postReviewBtn.setEnabled(false)
        viewModel.addReviewResponse.observe(viewLifecycleOwner, Observer {
            if (it.statusCode == 200 || it.statusCode == 204){
                binding.fragmentRatingProgressBarPb.visibility = View.GONE
                binding.reviewEdt.text.clear()
                postReviewBtn.setEnabled(true)
                findNavController().popBackStack()
                Snackbar.make(requireView(), "Successful", Snackbar.LENGTH_SHORT).show()
            }else{
                binding.fragmentRatingProgressBarPb.visibility = View.GONE
                postReviewBtn.setEnabled(true)
                Snackbar.make(requireView(), "was not successful", Snackbar.LENGTH_SHORT).show()
            }
        })
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
}