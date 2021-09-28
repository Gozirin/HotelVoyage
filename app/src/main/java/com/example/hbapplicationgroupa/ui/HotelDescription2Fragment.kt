package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.adapter.HotelGalleryAdapter
import com.example.hbapplicationgroupa.adapter.HotelRoomServiceRecyclerViewAdapter
import com.example.hbapplicationgroupa.adapter.StackedReviewAdapter
import com.example.hbapplicationgroupa.adapter.StackedReviewItemDecorator
import com.example.hbapplicationgroupa.databinding.FragmentHotelDescription2Binding
import com.example.hbapplicationgroupa.model.HotelGalleryModel
import com.example.hbapplicationgroupa.model.HotelRoomServiceModel
import com.example.hbapplicationgroupa.model.StackedReviewModel


/**
 * The Fragment displays hotel descriptions; from gallery, to reviews, amenities, price etc.
 * All id from the xml layout starts with a suffix of "hotel_desc"
 * There are two RecyclerViews attached to this fragment: HotelRoomServiceRecyclerView & HotelOverlayReviewRecyclerView
 */
class HotelDescription2Fragment : Fragment() {
    //Set up view binding here
    private var _binding: FragmentHotelDescription2Binding? = null
    private val binding get() = _binding!!
    //late-initializing variables
    private lateinit var hotelRoomServiceRecyclerViewAdapter: HotelRoomServiceRecyclerViewAdapter
    lateinit var hotelRoomList: ArrayList<HotelRoomServiceModel>
    lateinit var stackedReviewAdapter: StackedReviewAdapter
    lateinit var stackedImageList: ArrayList<StackedReviewModel>
    lateinit var stackedReviewLayoutManager: LinearLayoutManager
    lateinit var stackedReviewDecorator: StackedReviewItemDecorator
    lateinit var hotelGalleryAdapter: HotelGalleryAdapter
    lateinit var galleryList: ArrayList<HotelGalleryModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Enabled view binding here
        _binding = FragmentHotelDescription2Binding.inflate(inflater, container, false)
        return binding.root
    }

    //TODO: UI manipulation can be done here
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Setting fake list to HotelRoomServiceRecyclerView Adapter
        hotelRoomList = HotelRoomServiceModel.roomDataList
        hotelRoomServiceRecyclerViewAdapter = HotelRoomServiceRecyclerViewAdapter(hotelRoomList)

        //Setting fake list to StackedReview Adapter
        stackedReviewAdapter = StackedReviewAdapter()
        stackedImageList = StackedReviewModel.imgList
        stackedReviewAdapter.stackedImageList = stackedImageList

        //Setting fake list to HotelGallery Adapter
        hotelGalleryAdapter = HotelGalleryAdapter()
        galleryList = HotelGalleryModel.galleryList
        hotelGalleryAdapter.galleryList = galleryList

        viewClickListeners()
        initStackedReviewRecyclerView()
        initHotelRoomServiceRecyclerView()
        initHotelGalleryViewPager()
    }

    //Method Triggering onClickEvents
    private fun viewClickListeners(){
        //Click listener on view with Gallery text
        binding.hotelDescGalleryView.setOnClickListener {
            binding.hotelDescIv.setCurrentItem(binding.hotelDescIv.currentItem + 1, true)
        }

        //Click listener on back btn
        binding.hotelDescBackIv.setOnClickListener {
            //backStack
        }

        //Click listener on TextView with Show in Map text
        binding.hotelDescShowInMapLinkTv.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.hotel_desc_toast_string_two), Toast.LENGTH_SHORT).show()
        }
        //Click Listener for More button
        binding.hotelDescAmenitiesMoreIb.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.hotel_desc_toast_string_three), Toast.LENGTH_SHORT).show()
        }
        //Click Listener for Book button
        binding.hotelDescBookBtn.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.hotel_desc_toast_string_four), Toast.LENGTH_SHORT).show()
        }
        //Click Listener for BookMark Button
        binding.hotelDescBookmarkBtn.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.hotel_desc_toast_string_five), Toast.LENGTH_SHORT).show()
        }
    }

    //Method setting HotelRoomServiceViewPager attributes
    private fun initHotelRoomServiceRecyclerView(){
        binding.hotelDescServicesRecyclerView.apply {
            adapter = hotelRoomServiceRecyclerViewAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    //Method setting StackedReviewRecyclerView attributes
    private fun initStackedReviewRecyclerView(){
        binding.hotelDescOverlapRv.apply {
            adapter = stackedReviewAdapter
            stackedReviewDecorator = StackedReviewItemDecorator()
            addItemDecoration(stackedReviewDecorator)
            stackedReviewLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            stackedReviewLayoutManager.reverseLayout = true
            stackedReviewLayoutManager.stackFromEnd = true
            layoutManager = stackedReviewLayoutManager
        }
    }

    //Method setting HotelGalleryViewPager attributes
    private fun initHotelGalleryViewPager(){
        binding.hotelDescIv.apply {
            adapter = hotelGalleryAdapter
        }
    }

}