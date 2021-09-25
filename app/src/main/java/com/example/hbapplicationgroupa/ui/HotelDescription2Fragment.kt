package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.hbapplicationgroupa.HorizontalMarginItemDecoration
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.adapter.HotelRoomServiceViewPagerAdapter
import com.example.hbapplicationgroupa.databinding.FragmentHotelDescription2Binding
import com.example.hbapplicationgroupa.model.HotelRoomServiceModel

class HotelDescription2Fragment : Fragment() {
    //Set up view binding here
    private var _binding: FragmentHotelDescription2Binding? = null
    private val binding get() = _binding!!
    lateinit var hotelRoomServiceViewPagerAdapter: HotelRoomServiceViewPagerAdapter
    lateinit var hotelRoomList: ArrayList<HotelRoomServiceModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Enabled view binding here
        _binding = FragmentHotelDescription2Binding.inflate(inflater, container, false)
        return binding.root
    }

    //TODO: UI manipulation can be done here
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hotelRoomList = HotelRoomServiceModel.roomDataList
        hotelRoomServiceViewPagerAdapter = HotelRoomServiceViewPagerAdapter(hotelRoomList)
        initHotelRoomServiceViewPager()
        animateHotelRoomServiceViewPager()
    }

    //Method initializing HotelRoomServiceViewPager attributes
    private fun initHotelRoomServiceViewPager(){
        binding.hotelDescServicesViewPager.apply {
            adapter = hotelRoomServiceViewPagerAdapter
        }
    }

    //Method for animating HotelRoomServiceViewPager
    private fun animateHotelRoomServiceViewPager(){
        binding.hotelDescServicesViewPager.offscreenPageLimit = 1

        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            // Scaling the item's height.
            page.scaleY = 1 - (0.25f * Math.abs(position))
        }
        binding.hotelDescServicesViewPager.setPageTransformer(pageTransformer)
        // The ItemDecoration gives the current (centered) item horizontal margin so that
        // it doesn't occupy the whole screen width. Without it the items overlap
        val itemDecoration = HorizontalMarginItemDecoration(
            requireContext(),
            R.dimen.viewpager_current_item_horizontal_margin
        )
        binding.hotelDescServicesViewPager.addItemDecoration(itemDecoration)
    }
}