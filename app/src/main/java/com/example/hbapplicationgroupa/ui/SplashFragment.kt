package com.example.hbapplicationgroupa.ui

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.databinding.FragmentSplashScreenBinding

class SplashFragment : Fragment() {
    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("GKB", "onViewCreated: STARTED APP")
//        scaleText()
//        navigateToNextFragment()
    }

    //Create function that animates text in splash screen view
    private fun scaleText() {
        val appNameView = binding.fragmentSplashScreenHotelVoyageVw
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 4f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 4f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(appNameView, scaleX, scaleY)

        animator.duration = 3000
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()
    }

    //create function that moves to the next fragment
    private fun navigateToNextFragment(){
        val handler = Handler()
        handler.postDelayed({
            findNavController().navigate(R.id.action_splashScreenFragment_to_onboarding01Fragment2)
        }, 3000)
    }
}