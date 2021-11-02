package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.adapter.wishlistadapter.WishListAdapter
import com.example.hbapplicationgroupa.databinding.FragmentWishListBinding
import com.example.hbapplicationgroupa.viewmodel.CustomerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishListFragment : Fragment(), WishListAdapter.WishListItemClickListener, WishListAdapter.WishListBookButtonClickListener {

    //initializing vm and recyclerview
    val customerViewModel: CustomerViewModel by viewModels()
    lateinit var wishListRecyclerView: RecyclerView
    lateinit var wishListAdapter: WishListAdapter

    private var _binding: FragmentWishListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWishListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wishListRecyclerView = binding.wishListRecyclerView
        //show progress bar while pulling api data

        //setting recycler view
        setupRecyclerView()

        showProgressBar()
        //observing data and setting it on the recyclerView
        customerViewModel.getWishList()
        customerViewModel.wishListLiveData.observe(viewLifecycleOwner, {
            if (it.isEmpty()) {
                Log.d("wishList Observer", "No data from the Vm")
                binding.noWishListTxt.text = "You have no items saved"
                hideProgressBar()
            } else {
                wishListAdapter.setDataToAdapter(it)
                hideProgressBar()
                Log.d("wishList Observer", it.toString())
            }
        })

        //TODO: Set click listener on recycler view item
        binding.appBarTitle.setOnClickListener {
            findNavController().navigate(R.id.action_wishListFragment_to_bookingDetailsFragment)
        }

        //TODO: Set click listener on recycler view item
        binding.searchBar.setOnClickListener {
            findNavController().navigate(R.id.action_wishListFragment_to_hotelDescription2Fragment)
        }

        //Overriding onBack press to navigate to home Fragment onBack Pressed
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_wishListFragment_to_exploreHomeFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    //Click listener for navigation of saved items to hotel description fragment
    override fun wishListClicked(position: Int) {
        findNavController().navigate(R.id.action_wishListFragment_to_hotelDescription2Fragment)
    }

    //Click listener for navigation of book btn to booking details
    override fun wishListBookBtnClicked(position: Int) {
        findNavController().navigate(R.id.action_wishListFragment_to_bookingDetailsFragment)
    }

    //setting adapter
    //set up recycler view
    private fun setupRecyclerView() {
        wishListAdapter = WishListAdapter(requireContext(), this, this)
        wishListRecyclerView.apply {
            adapter = wishListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun hideProgressBar() {
        binding.wishListProgressBar.visibility = View.GONE
        binding.tvNotificationWishList.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.wishListProgressBar.visibility = View.VISIBLE
        binding.tvNotificationWishList.visibility = View.VISIBLE
    }
}