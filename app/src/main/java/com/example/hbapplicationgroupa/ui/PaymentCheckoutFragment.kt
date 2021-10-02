package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.databinding.FragmentPaymentCheckoutBinding

class PaymentCheckoutFragment : Fragment() {
    //Set up view binding here
    private var _binding: FragmentPaymentCheckoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Enabled view binding here
        _binding = FragmentPaymentCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        //navigating to next fragment
//        val navigateToNextFragment = binding.paymentOptionBackBtn
//            navigateToNextFragment.setOnClickListener{
//                val navController = Navigation.findNavController(requireActivity(), R.id.navigation_graph)
//                //Add destination here
//                //navController.navigate(R.id.action................)
//            }
//
//        //back arrow navigation
//        val backArrow = binding.paymentOptionBackBtn
//        backArrow.setOnClickListener{
//            val navController = Navigation.findNavController(requireActivity(), R.id.navigation_graph)
//            navController.navigateUp()
//        }
    }
}