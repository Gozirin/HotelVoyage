package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.databinding.FragmentWriteAReviewBinding

/**
 * This Fragment is where users write reviews and post
 * View id in the Fragment start with suffix of; "review_"
 */
class WriteAReviewFragment : Fragment() {
    private var _binding: FragmentWriteAReviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWriteAReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.reviewBackBtn.setOnClickListener {
            findNavController().navigate(R.id.action_writeAReviewFragment_to_ratingFragment)
        }

        binding.reviewPostBtn.setOnClickListener {
            findNavController().navigate(R.id.action_writeAReviewFragment_to_ratingFragment)
        }

        onBackPressed()
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