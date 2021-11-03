package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.adapter.wishlistadapter.WishListAdapter
import com.example.hbapplicationgroupa.database.AuthPreference
import com.example.hbapplicationgroupa.databinding.FragmentWishListBinding
import com.example.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber.PageItem
import com.example.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber.WishlistByPageNumberResponseItems
import com.example.hbapplicationgroupa.viewmodel.CustomerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishListFragment : Fragment(),
                         WishListAdapter.WishListItemClickListener,
                         WishListAdapter.WishListPreviewButtonClickListener,
                         WishListAdapter.WishListRemoveButtonClickListener
                        {
//    private var _binding: FragmentWishListBinding? = null
//    private val binding get() = _binding!!
//class WishListFragment : Fragment(), WishListAdapter.WishListItemClickListener, WishListAdapter.WishListBookButtonClickListener {

    //initializing vm and recyclerview
    val customerViewModel: CustomerViewModel by viewModels()
    private lateinit var wishListRecyclerView : RecyclerView

    private lateinit var arrayLists : List<PageItem>
    private lateinit var selectedState: String
    private lateinit var hotelId: String
    private lateinit var wishListAdapter: WishListAdapter

    private var _binding: FragmentWishListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWishListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arrayLists = listOf()

        wishListRecyclerView = binding.wishListRecyclerView

        //show progress bar while pulling api data

        //setting recycler view
        setupRecyclerView()

        showProgressBar()
        //observing data and setting it on the recyclerView
            AuthPreference.initPreference(requireActivity())
            val authToken = "Bearer ${AuthPreference.getToken(AuthPreference.TOKEN_KEY)}"

            customerViewModel.getWishList(authToken, 10, 1)
            customerViewModel.wishListLiveData.observe(viewLifecycleOwner, {
                if (it.isNullOrEmpty()) {
                    Log.d("wishList fragError", "No data from the Vm")
                    hideProgressBar()
                    Toast.makeText(requireContext(), "No WishList Saved", Toast.LENGTH_LONG).show()
                } else {
                    arrayLists = it
                    wishListAdapter.setDataToAdapter(it)
                    hideProgressBar()
                    Log.d("wishList fragSuccess", it.toString())
                }
            })

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
        val item = arrayLists[position]
        hotelId = item.hotelId!!
        val action = AllHotelsFragmentsDirections.actionAllHotelsFragmentsToHotelDescription2Fragment(hotelId)
        findNavController().navigate(action)
    }

    //Click listener for navigation of book btn to booking details
    override fun wishListPreviewBtnClicked(position: Int) {val item = arrayLists[position]
        hotelId = item.hotelId!!
        val action = AllHotelsFragmentsDirections.actionAllHotelsFragmentsToHotelDescription2Fragment(hotelId)
        findNavController().navigate(action)
    }

    //remove icon item click
    override fun wishlistRemoveBtnClicked(position: Int) {
        AuthPreference.initPreference(requireActivity())
        val authToken = "Bearer ${AuthPreference.getToken(AuthPreference.TOKEN_KEY)}"
        customerViewModel.removeWishList(authToken, arrayLists[position].hotelId!!)
        customerViewModel.getWishList(authToken, 10, 1)
        Toast.makeText(requireContext(),
            "${arrayLists[position].hotelName} is successfully deleted from WishList",
            Toast.LENGTH_SHORT).show()
    }

    //setting adapter
    //set up recycler view
    private fun setupRecyclerView() {
        wishListAdapter = WishListAdapter(requireContext(),
                    this,
            this,
            this)
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