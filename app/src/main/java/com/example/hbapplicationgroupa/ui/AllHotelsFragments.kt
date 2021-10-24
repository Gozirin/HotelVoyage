package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.adapter.allHotelsAdapter.AllHotelsAdapter
import com.example.hbapplicationgroupa.databinding.FragmentAllHotelsFragmentBinding
import com.example.hbapplicationgroupa.model.hotelmodule.allhotels.PageItem
import com.example.hbapplicationgroupa.model.hotelmodule.filterallhotelbylocation.FilterAllHotelByLocation
import com.example.hbapplicationgroupa.viewModel.HotelViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllHotelsFragments : Fragment(), AllHotelsAdapter.AllHotelsItemClickListener, AllHotelsAdapter.AllHotelsBookBtnClickListener {

    lateinit var allHotelsAdapter: AllHotelsAdapter
    val viewModel: HotelViewModel by viewModels()
    val arrayList =  ArrayList<PageItem>()
    lateinit var selectedState: String


    //setting up view binding
    private var _binding: FragmentAllHotelsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllHotelsFragmentBinding.inflate(
            inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Handling on back icon to go back to explore page
        binding.allHotelsBackBtn.setOnClickListener{ findNavController().popBackStack()}

        //setting recyclerview
        // To filter all hotel location
        val  autoCompleteTextView = binding.allHotelsFilters
        val languages = resources.getStringArray(R.array.states)
        val filterByAdapter = ArrayAdapter(requireContext(), R.layout.allhotel_autocompletetv_xml, languages)
            autoCompleteTextView.setAdapter(filterByAdapter)

        // to set filter_By textView to Location textView on the screen
        binding.allHotelsFilters.onItemClickListener = object :AdapterView.OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
              selectedState = languages[p2].toString()
//                if (selectedState.isEmpty()){
//                    binding.tvNotificationAllHotels.visibility = View.VISIBLE
//                    allHotelsAdapter.notifyDataSetChanged()
//                    binding.tvNotificationAllHotels.visibility = View.VISIBLE
//                }else{
//                 //   search(selectedState)
//                }
             //search(selectedState)
                binding.allHotelsLocationTxt.text = selectedState
                binding.allHotelsLocationTxt.visibility = View.VISIBLE

            }
        }

        onBackPressed()
        setupRecyclerView()
        //showing progress bar while api data is loading or no internet
        showProgressBar()
        filterAllHotelByLocationObserver()

        //Observing viewModel
        viewModel.fetchAllHotels()
        viewModel.allHotelsLiveData.observe(viewLifecycleOwner, { response ->
            response.pageItems.let {
                Log.d("ObservingVM", response.pageItems.toString())
                if (it != null) {
                    allHotelsAdapter.listOfAllHotels.addAll(it)
                    hideProgressBar()
                    allHotelsAdapter.notifyDataSetChanged()
                }
            }
        })

    }


    override fun allHotelsItemClicked(position: Int) {
        findNavController().navigate(R.id.action_allHotelsFragments_to_hotelDescription2Fragment)
    }

    override fun allHotelsBookBtnClicked(position: Int) {
        findNavController().navigate(R.id.action_allHotelsFragments_to_bookingDetailsFragment)
    }

    private fun hideProgressBar() {
        binding.fragmentAllHotelsProgressBarPb.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.fragmentAllHotelsProgressBarPb.visibility = View.VISIBLE
        Toast.makeText(requireContext(), " Please, make sure your Internet is active", Toast.LENGTH_LONG).show()
    }

    //set up recycler view
    private fun setupRecyclerView() {
        allHotelsAdapter = AllHotelsAdapter(this, this)
        binding.allHotelsRecyclerview.apply {
            adapter = allHotelsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
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


    fun makeApiCallFilterAllHotelByLocation(location: String, pageSize: Int, pageNumber: Int){
        viewModel.filterAllHotelWithLocation(location, pageSize, pageNumber)
    }

    fun filterAllHotelByLocationObserver(){
        viewModel._filterAllHotelByLocationLiveData.observe(requireActivity(),{
            if (it == null){
                Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
            }else{
        allHotelsAdapter.listOfAllHotels = it.data?.pageItems as MutableList<PageItem>
                allHotelsAdapter.notifyDataSetChanged()
                Log.d("All hotels: ", "${it.data.pageItems}")
                Toast.makeText(requireContext(),"${it.data.pageItems}", Toast.LENGTH_SHORT).show()


            }

        })

    }

    fun search(selectedState: String) {
        val selectedState =  viewModel.search(selectedState)
        Log.d("Selected State", selectedState.toString())
        allHotelsAdapter.setList(selectedState)

    }
}