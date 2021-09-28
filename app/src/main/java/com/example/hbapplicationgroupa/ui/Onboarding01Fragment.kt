package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Enabled view binding here
        _binding = FragmentOnboarding01Binding.inflate(inflater, container, false)
        return binding.root
    }

    //TODO: UI manipulation can be done here
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpOnBoardingPage()
        setUpIndicator()
    }

    //create method to set up the onBoarding pages
    private fun setUpOnBoardingPage() {
        onBoardingAdapter = OnBoardingViewPagerAdapter()
        binding.viewpager2.adapter = onBoardingAdapter
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

        onBoardingAdapter.onBoardingInfo = onBoardingList

        binding.viewpager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                isCurrentIndicator(position)
                textChangeOnClickEvent(position)
            }
        } )
        (binding.viewpager2.getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        binding.fragmentOnBoarding01OptionTv.setOnClickListener {
            if (binding.viewpager2.currentItem + 1 < onBoardingAdapter.itemCount) {
                binding.viewpager2.currentItem += 1
            }else {
                navigateToRegisterFragment()
            }
        }
    }

    private fun setUpIndicator() {
        val indicator = arrayOfNulls<ImageView>(onBoardingAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
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

    private fun defaultOnClickViewText() {
        binding.fragmentOnBoarding01OptionTv.text = "Skip"
        binding.fragmentOnBoarding01ChangeViewButton.text = "Next"
    }

    private fun changeOnClickViewText() {
        binding.fragmentOnBoarding01OptionTv.text = "Done"
        binding.fragmentOnBoarding01ChangeViewButton.text = "Get Started"
    }

    fun  isCurrentIndicator(position: Int) {
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
            }else {
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

    fun textChangeOnClickEvent(position: Int) {
        if (position < onBoardingAdapter.itemCount - 1) {
            defaultOnClickViewText()
        }else {
            changeOnClickViewText()
        }
    }

    private fun navigateToRegisterFragment() {
        findNavController().navigate(R.id.action_onboarding01Fragment2_to_registerFragment)
    }
}