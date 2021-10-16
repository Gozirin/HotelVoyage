package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.adapter.topdeal.TopDealAdapter
import com.example.hbapplicationgroupa.databinding.FragmentTopDealsBinding
import com.example.hbapplicationgroupa.utils.QUERY_PAGE_SIZE
import com.example.hbapplicationgroupa.utils.Resource
import com.example.hbapplicationgroupa.viewModel.HotelViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopDealsFragment : Fragment(), TopDealAdapter.TopDealItemClickListener, TopDealAdapter.TopDealBookBtnClickListener {

    private lateinit var topDealAdapter: TopDealAdapter
    val viewModel: HotelViewModel by viewModels()

    //Set up view binding here
    private var _binding: FragmentTopDealsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTopDealsBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel._topDealsLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {response->
            when (response){
                is Resource.Success ->{
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        topDealAdapter.topDealList = newsResponse.data.toList()
                        topDealAdapter.notifyDataSetChanged()
                        val totalPages = 6000 / QUERY_PAGE_SIZE + 2
                        isLastPage = viewModel.pageNumber == totalPages
                    }

                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e("TAG", "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }

        })

        binding.topDealBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }


        onBackPressed()
    }
    private fun hideProgressBar() {
        binding.fragmentTopDealsProgressBarPb.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        binding.fragmentTopDealsProgressBarPb.visibility = View.VISIBLE
        isLoading = true
    }
    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    val scrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount


            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning &&
                    isTotalMoreThanVisible && isScrolling
            if(shouldPaginate) {
                viewModel.getTopDealss(10)
                isScrolling = false
            } else {
                binding.topDealRecyclerview.setPadding(0, 0, 0, 0)
            }
        }
    }



    override fun topHotelsItemClicked(position: Int) {
        findNavController().navigate(R.id.action_topDealsFragment_to_hotelDescription2Fragment)
    }

    override fun topHotelsBookBtnClicked(position: Int) {
        findNavController().navigate(R.id.action_topDealsFragment_to_bookingDetailsFragment)
    }

    //Method to handle back press
    private fun onBackPressed(){
        //Overriding onBack press to navigate to home Fragment onBack Pressed
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }
    private fun setupRecyclerView() {
        topDealAdapter = TopDealAdapter(this, this)
        binding.topDealRecyclerview.apply {
            adapter = topDealAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addOnScrollListener(this@TopDealsFragment.scrollListener)
        }

    }


}