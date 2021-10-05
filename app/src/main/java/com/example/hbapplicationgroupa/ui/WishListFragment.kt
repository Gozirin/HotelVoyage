package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.adapter.wishlistadapter.WishListAdapter
import com.example.hbapplicationgroupa.databinding.FragmentWishListBinding
import com.example.hbapplicationgroupa.model.WishListData

class WishListFragment : Fragment(), WishListAdapter.WishListItemClickListener, WishListAdapter.WishListBookButtonClickListener {
    private var _binding: FragmentWishListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWishListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // this is a temporary data, to be removed when the required data is available
        val item1 = WishListData("\$2334", "BeachTowa", "5 Star hotel.96%")
        val item2 = WishListData("\$6339", "Arizona Hotel", "3 Star hotel.90%")
        val item3 = WishListData("\$4338", "Eko Hotel", "4 Star hotel.93%")
        val item4 = WishListData("\$8374", "Sharaton", "5 Star hotel.96%")
        val item5 = WishListData("\$1354", "Edo lunge", "4 Star hotel.93%")

        val items = arrayListOf(item1, item2, item3, item4, item5)

        val wishlistRecyclerView = binding.wishListRecyclerView
        wishlistRecyclerView.layoutManager = LinearLayoutManager(context)

        // initialise the adapter and pass the items to the adapter
        val adapter = WishListAdapter(items, requireContext(), this, this)
        wishlistRecyclerView.adapter = adapter

        //TODO: Set click listener on recycler view item
        binding.appBarTitle.setOnClickListener {
            findNavController().navigate(R.id.action_wishListFragment_to_bookingDetailsFragment)
        }

        //TODO: Set click listener on recycler view item
        binding.searchBar.setOnClickListener {
            findNavController().navigate(R.id.action_wishListFragment_to_hotelDescription2Fragment)
        }
    }

    //Click listener for navigation of saved items to hotel description fragment
    override fun wishListClicked(position: Int) {
        findNavController().navigate(R.id.action_wishListFragment_to_hotelDescription2Fragment)
    }

    //Click listener for navigation of book btn to booking details
    override fun wishListBookBtnClicked(position: Int) {
        findNavController().navigate(R.id.action_wishListFragment_to_bookingDetailsFragment)
    }


}