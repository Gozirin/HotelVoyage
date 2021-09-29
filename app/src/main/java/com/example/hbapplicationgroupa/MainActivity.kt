package com.example.hbapplicationgroupa

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.hbapplicationgroupa.databinding.ActivityMainBinding

class MainActivity:AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
       setContentView(binding.root)

    /** This is the setup for bottom navigation bar that is used to navigate to explore fragment,
        Wishlist fragment and Profile fragment
    */
    val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
    val navController = navHostFragment.findNavController()
    binding.bottomNavigationBar.setupWithNavController(navController)

//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            if(destination.id == R.id.) {
//
//                binding.bottomNavigationBar.visibility = View.GONE
//            } else {
//
//                binding.bottomNavigationBar.visibility = View.VISIBLE
//            }
//        }

    }
}
