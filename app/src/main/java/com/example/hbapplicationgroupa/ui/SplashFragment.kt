package com.example.hbapplicationgroupa.ui

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.databinding.FragmentSplashScreenBinding

class SplashFragment : Fragment() {
    //Set up view binding here
    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Enabled view binding here
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    //TODO: UI manipulation can be done here
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        scaleText()
        navigateToNextFragment()


    }
    //create function that animates text in splash screen view
    private fun scaleText() {
        val appNameView = binding.fragmentSplashScreenHotelVoyageVw
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 400f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 200f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(
            appNameView, scaleX, scaleY)
        animator.duration = 5000
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()
    }

    //create function that moves to the next fragment
    private fun navigateToNextFragment(){
        val handler = Handler()
        handler.postDelayed({
//            findNavController().navigate(R.id.action_splashScreenFragment_to_onboarding01Fragment2)
        }, 5000)
    }

}