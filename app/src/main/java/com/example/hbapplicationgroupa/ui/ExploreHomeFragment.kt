package com.example.hbapplicationgroupa.ui

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.paystack.android.PaystackSdk.applicationContext
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.adapter.exploreHomeAdapter.ExploreHomeTopDealsAdapter
import com.example.hbapplicationgroupa.adapter.exploreHomeAdapter.ExploreHomeTopHotelsAdapter
import com.example.hbapplicationgroupa.adapter.topHotel.TopHotelAdapter
import com.example.hbapplicationgroupa.databinding.FragmentExploreHomeBinding
import com.example.hbapplicationgroupa.viewModel.HotelViewModel
import com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseItem
import com.example.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseItem
import com.example.hbapplicationgroupa.utils.ConnectivityLiveData
import com.example.hbapplicationgroupa.utils.Status

import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext

@AndroidEntryPoint
class ExploreHomeFragment : Fragment(), ExploreHomeTopHotelsAdapter.TopHotelClickListener, ExploreHomeTopDealsAdapter.TopDealsClickListener {

    private lateinit var topHotelsAdapter: ExploreHomeTopHotelsAdapter
    private lateinit var topHotelsScreenAdapter: TopHotelAdapter
    private lateinit var topDealsAdapter: ExploreHomeTopDealsAdapter
    private lateinit var topHotelsRecyclerView: RecyclerView
    private lateinit var topDealsRecyclerView: RecyclerView
    private lateinit var dialog: Dialog
    private val hotelViewModel: HotelViewModel by viewModels()
    private lateinit var connectivityLiveData: ConnectivityLiveData


    //Set up view binding here
    private var _binding: FragmentExploreHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Enabled view binding here
        _binding = FragmentExploreHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog = Dialog(requireContext())
        onBackPressed()

        connectivityLiveData = ConnectivityLiveData(requireActivity().application)

        topHotelsAdapter = ExploreHomeTopHotelsAdapter( this)
        topDealsAdapter = ExploreHomeTopDealsAdapter( this)

        setUpTopHotelsRecyclerView()
        setUpTopDealsRecyclerView()
        checkNetworkStatus()


        //navigating to topHotel Fragment
        binding.exploreHomeFragmentTopHotelViewAllTv.setOnClickListener {
           findNavController().navigate(R.id.action_exploreHomeFragment_to_topHotelsFragment)
        }
        //click listener for View button navigation to all hotel fragment
        binding.exploreHomeViewAndArrowBtn.setOnClickListener {
            findNavController().navigate(R.id.action_exploreHomeFragment_to_allHotelsFragments)
        }
        //navigation to top Deal Fragment
        binding.exploreHomeFragmentTopDealsViewAllTv.setOnClickListener {
           findNavController().navigate(R.id.action_exploreHomeFragment_to_topDealsFragment)
        }
        //navigation to all hotels screen
        binding.exploreHomeAllHotelsTv.setOnClickListener {
            findNavController().navigate(R.id.action_exploreHomeFragment_to_allHotelsFragments)
        }
    }

    private fun onBackPressed (){
        //Overriding onBack press to finish activity and exit app
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                dialog.setContentView(R.layout.exit_dialog)
                dialog.show()
                dialogActivities()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }
    private fun dialogActivities(){
        //logout
        val logout = dialog.findViewById<TextView>(R.id.exit_dialogLogout)
        logout.setOnClickListener {
            dialog.dismiss()
            requireActivity().finish()
            requireActivity().finishAffinity()
        }

        //cancel log out event
        val cancel = dialog.findViewById<TextView>(R.id.exit_dialogCancel)
        cancel.setOnClickListener {
            dialog.dismiss()
        }
    }


    override fun onTopHotelClicked(position: Int, hotelId: String) {
        //Click listener for Top hotel click listeners
        val action = ExploreHomeFragmentDirections.actionExploreHomeFragmentToHotelDescription2Fragment(hotelId)
        findNavController().navigate(action)
    }

    override fun topDealsClicked(position: Int, hotelId: String) {
        //Click Listener for Top Deal Click Listeners
        val action = ExploreHomeFragmentDirections.actionExploreHomeFragmentToHotelDescription2Fragment(hotelId)
        findNavController().navigate(action)
    }

    //set up top hotels recycler view
    private fun setUpTopHotelsRecyclerView() {
        topHotelsRecyclerView = requireView().findViewById(R.id.exploreHomeFragmentRecyclerView1)
        topHotelsRecyclerView.adapter = topHotelsAdapter
        topHotelsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        topHotelsRecyclerView.setHasFixedSize(true)
    }

    //set up top deals recycler view
    private fun setUpTopDealsRecyclerView() {
        topDealsRecyclerView = requireView().findViewById(R.id.exploreHomeFragmentRecyclerView2)
        topDealsRecyclerView.adapter = topDealsAdapter
        topDealsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        topDealsRecyclerView.setHasFixedSize(true)
    }

    //fetch a selected number of top hotels
    private fun getTopHotels(): HotelViewModel {
//        binding.exploreHomeFragmentProgressBar1.visibility = View.VISIBLE
//        binding.exploreHomeFragmentRecyclerView1.visibility = View.GONE
        hotelViewModel.fetchTopHotels()
        hotelViewModel.exploreHomeTopHotels.observe( viewLifecycleOwner, {
            if (it != null) {
               // binding.exploreHomeFragmentProgressBar1.visibility = View.GONE
                it.data.let { topHotels -> renderTopHotelsList(topHotels) }
                binding.exploreHomeFragmentRecyclerView1.visibility = View.VISIBLE
            }
//            when (it.statusCode) {
//                200 -> {
//                    binding.exploreHomeFragmentProgressBar1.visibility = View.GONE
//                    it.data.let { topHotels -> renderTopHotelsList(topHotels) }
//                    binding.exploreHomeFragmentRecyclerView1.visibility = View.VISIBLE
//                   // Log.d("ExploreHome 2: ", it.toString())
//                }
//
//                400 -> {
//                    //handle error
//                    binding.exploreHomeFragmentProgressBar1.visibility = View.GONE
//                    Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_LONG).show()
//                }
//            }
        })
//        connectivityLiveData.observe(viewLifecycleOwner, Observer { isAvailable ->
//            //2
//            when (isAvailable) {
//                true -> {
//                    //3
//                    hotelViewModel.exploreHomeTopHotels
//                }
//                false -> {
//                    binding.exploreHomeFragmentTopHotelErrorMsg.visibility = View.VISIBLE
//                    //Toast.makeText(this, "Error fetching data, please refresh App", Toast.LENGTH_SHORT).show()
//                }
//            }
//        })
       return hotelViewModel
    }

    //set fetched data to top hotels adapter
    private fun renderTopHotelsList(topHotels: List<GetTopHotelsResponseItem>) {
        topHotelsAdapter.setListOfTopHotels(topHotels)
        topHotelsAdapter.notifyDataSetChanged()
    }

    //fetch a selected number of top deals
    private fun getTopDeals(): HotelViewModel {
//        binding.exploreHomeFragmentProgressBar2.visibility = View.VISIBLE
//        binding.exploreHomeFragmentRecyclerView2.visibility = View.GONE
        hotelViewModel.fetchTopDeals()
        hotelViewModel.exploreHomeTopDeals.observe(viewLifecycleOwner, {
            if (it != null) {
              //  binding.exploreHomeFragmentProgressBar2.visibility = View.GONE
                it.data.let { topDeals -> renderTopDealsList(topDeals) }
                binding.exploreHomeFragmentRecyclerView2.visibility = View.VISIBLE
            }
//            when (it.statusCode) {
//                200 -> {
//                    binding.exploreHomeFragmentProgressBar2.visibility = View.GONE
//                    it.data.let { topDeals -> renderTopDealsList(topDeals) }
//                    binding.exploreHomeFragmentRecyclerView2.visibility = View.VISIBLE
//                    //Log.d("ExploreHome 1: ", it.toString())
//                }
//
//                400 -> {
//                    binding.exploreHomeFragmentProgressBar2.visibility = View.GONE
//                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
//                }
//            }
        })
//        connectivityLiveData.observe(viewLifecycleOwner, Observer { isAvailable ->
//            //2
//            when (isAvailable) {
//                true -> {
//                    //3
//                    hotelViewModel.exploreHomeTopDeals
//                }
//                false -> {
//                    binding.exploreHomeFragmentTopHotelErrorMsg.visibility = View.VISIBLE
//                    //Toast.makeText(this, "Error fetching data, please refresh App", Toast.LENGTH_SHORT).show()
//                }
//            }
//        })
        return  hotelViewModel
    }





//    fun initViewModel(pageSize: Int, pageNumber: Int){
//        hotelViewModel.getHotelFromApi(pageSize,pageNumber)
//
//    }

//    fun makeApiCall(){
//       hotelViewModel.allHotelsLivedata.observe(requireActivity(),{
//            if (it == null){
//               Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
//         }else{
//
//
//           }
//        })
//    }

    //set fetched data to top deals adapter
    private fun renderTopDealsList(topDeals: List<GetTopDealsResponseItem>) {
        topDealsAdapter.setListOfTopDeals(topDeals)

    }

    private fun checkNetworkStatus() {
        binding.exploreHomeFragmentProgressBar1.visibility = View.VISIBLE
        binding.exploreHomeFragmentRecyclerView1.visibility = View.GONE
        binding.exploreHomeFragmentRecyclerView2.visibility = View.GONE

        connectivityLiveData.observe(viewLifecycleOwner, Observer { isAvailable ->
            //2
            when (isAvailable) {
                true -> {
                    //3
                    getTopHotels()
                    getTopDeals()
                }
                false -> {
                    binding.exploreHomeFragmentTopHotelErrorMsg.visibility = View.VISIBLE
                    //Toast.makeText(this, "Error fetching data, please refresh App", Toast.LENGTH_SHORT).show()
                }
            }
        })


    }



}