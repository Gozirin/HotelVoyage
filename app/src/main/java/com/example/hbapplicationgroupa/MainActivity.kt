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
showBottomNav()
        // set the bottom navigation in various fragment
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.wishListFragment -> showBottomNav()
                R.id.hotelDescription2Fragment -> showBottomNav()
                R.id.ratingFragment -> showBottomNav()
                R.id.writeAReviewFragment -> showBottomNav()
                R.id.topHotelsFragment -> showBottomNav()
                R.id.exploreHomeAfterSearchFragment -> showBottomNav()
                R.id.bookingConfirmationFragment -> showBottomNav()
                R.id.bookingDetailsFragment -> showBottomNav()
             else -> binding.bottomNavigationBar.visibility = View.GONE
            }
        }
    }
    // this function is used to show the navigation bar in a fragment
    private fun showBottomNav() {
        binding.bottomNavigationBar.visibility = View.VISIBLE

    }

    // this function is used to show the navigation bar in a fragment
    private fun hideBottomNav() {
        binding.bottomNavigationBar.visibility = View.GONE

    }
}
