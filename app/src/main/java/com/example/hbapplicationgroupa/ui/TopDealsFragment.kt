package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.adapter.topdeal.TopDealAdapter
import com.example.hbapplicationgroupa.databinding.FragmentTopDealsBinding
import com.example.hbapplicationgroupa.model.adaptermodels.TopDealModel
import com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseItem
import com.example.hbapplicationgroupa.viewmodelsss.HotelViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Observer

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

        topDealAdapter = TopDealAdapter(this, this)
        binding.topDealRecyclerview.apply {
            adapter = topDealAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



//        binding.topDealRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getTopDeals(10, 1)
        viewModel.topDealsLiveData.observe(viewLifecycleOwner,  { response->
            if (response.isSuccessful){
                topDealAdapter.topDealList = response.body()?.data!!
            }else{
                Toast.makeText(requireContext(), "${response.errorBody()}", Toast.LENGTH_SHORT).show()
            }
//            topDealAdapter.topDealList = it.data
            topDealAdapter.notifyDataSetChanged()
//            Toast.makeText(requireContext(), "${topDealAdapter.topDealList}", Toast.LENGTH_SHORT).show()
        } )




        binding.topDealBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }


        onBackPressed()
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


}