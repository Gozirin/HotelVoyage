package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.hbapplicationgroupa.OnBoardingInfo
import com.example.hbapplicationgroupa.OnBoardingViewPagerAdapter
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.databinding.FragmentOnboarding01Binding

class Onboarding01Fragment : Fragment() {
    //Set up view binding here
    private var _binding: FragmentOnboarding01Binding? = null
    private val binding get() = _binding!!
    private lateinit var onBoardingList: MutableList<OnBoardingInfo>
    private lateinit var onBoardingAdapter: OnBoardingViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Enabled view binding here
        _binding = FragmentOnboarding01Binding.inflate(inflater, container, false)
        return binding.root
    }

    //TODO: UI manipulation can be done here
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpOnBoardingPage()
        setUpIndicator()
        onClickOptionEvent()
    }

    //create method to set up the onBoarding pages
    private fun setUpOnBoardingPage() {
        onBoardingAdapter = OnBoardingViewPagerAdapter()    //initialise onboarding adapter
        binding.viewpager2.adapter = onBoardingAdapter         //assign the viewpager2 adapter to the onboarding adapter

        //initialise the onBoarding list using the model class onBoardingInfo. The onBoardingList contains the data to be listed in the viewpager
        onBoardingList = mutableListOf(
            OnBoardingInfo(
                R.drawable.onboarding_01_img,
                "Search and save your preference",
                "Browse best hotels from 40,000+ database that fits your unique needs"
            ),
            OnBoardingInfo(
                R.drawable.onboarding_02_img,
                "Find the best deals",
                "Find the best deals from any season and book from a curated list"
            ),
            OnBoardingInfo(
                R.drawable.onboarding_03_img,
                "Book and enjoy your stay",
                "Select the hotel and date as per your preference to book and have a pleasant stay"
            ),
        )

        onBoardingAdapter.onBoardingInfo = onBoardingList       //set the onBoardingList to the adapter

        binding.viewpager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                isCurrentIndicator(position)
                textChangeOnClickEvent(position)
                onClickChangeViewEvent(position)
            }
        })
        (binding.viewpager2.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER
    }
        //setup indicator
    private fun setUpIndicator() {
        val indicator = arrayOfNulls<ImageView>(onBoardingAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(12, 0, 12, 8)

        for (eachCircle in indicator.indices) {
            indicator[eachCircle] = ImageView(context)
            indicator[eachCircle]?.let {
                it.setImageDrawable(
                    context?.let { context ->
                        ContextCompat.getDrawable(
                            context, R.drawable.indicator_circle
                        )
                    }
                )
                it.layoutParams = layoutParams
                binding.circleIndicator.addView(it)
            }
        }

    }
//set default text in views
    private fun defaultOnClickViewText() {
        binding.fragmentOnBoarding01OptionTv.text = "Skip"
        binding.fragmentOnBoarding01ChangeViewButton.text = "Next"
    }
//set text when view is changed
    private fun changeOnClickViewText() {
        binding.fragmentOnBoarding01OptionTv.text = "Done"
        binding.fragmentOnBoarding01ChangeViewButton.text = "Get Started"
    }
//set the indicator light to the current page
    fun isCurrentIndicator(position: Int) {
        val childCount = binding.circleIndicator.childCount
        for (spot in 0 until childCount) {
            val view = binding.circleIndicator.getChildAt(spot) as ImageView
            if (spot == position) {
                view.setImageDrawable(
                    context?.let {
                        ContextCompat.getDrawable(
                            it, R.drawable.current_position_indicator
                        )
                    }
                )
            } else {
                view.setImageDrawable(
                    context?.let {
                        ContextCompat.getDrawable(
                            it, R.drawable.indicator_circle
                        )
                    }
                )
            }
        }
    }
//set function to display required text on click event
    fun textChangeOnClickEvent(position: Int) {
        if (position < onBoardingAdapter.itemCount - 1) {
            defaultOnClickViewText()
        } else {
            changeOnClickViewText()
        }
    }
//function to navigates to register fragment
    private fun navigateToRegisterFragment() {
        findNavController().navigate(R.id.action_onboarding01Fragment2_to_registerFragment)
    }

        //function that effects changes on click of options view
    private fun onClickOptionEvent() {
        binding.fragmentOnBoarding01OptionTv.setOnClickListener {
            navigateToRegisterFragment()
        }
    }

    //function that effects changes on click of change view button
    fun onClickChangeViewEvent(position: Int) {
        binding.fragmentOnBoarding01ChangeViewButton.setOnClickListener {
            if (position == onBoardingAdapter.itemCount - 1) {
                navigateToRegisterFragment()
            } else {
                binding.viewpager2.setCurrentItem(binding.viewpager2.currentItem + 1, true)
            }
        }
    }
}