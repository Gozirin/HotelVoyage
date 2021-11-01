package com.example.hbapplicationgroupa.ui

import android.content.Intent
import android.content.Intent.getIntent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.databinding.FragmentPaymentCheckoutBinding
import com.example.hbapplicationgroupa.model.hotelmodule.bookhotel.BookHotel
import com.example.hbapplicationgroupa.model.hotelmodule.bookhotel.VerifyBooking
import com.example.hbapplicationgroupa.viewModel.HotelViewModel

class PaymentCheckoutFragment : Fragment() {
    //Set up view binding here
    private var _binding: FragmentPaymentCheckoutBinding? = null
    private val binding get() = _binding!!
    private val hotelViewModel: HotelViewModel by viewModels()
  //  private var transactionReference = ""
    private var transactionURL = "https://checkout.paystack.com/bkxjrmwckm3fvpt"
    private var verificationDetails: VerifyBooking? = null
    private var selectedPaymentOption: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Enabled view binding here
        _binding = FragmentPaymentCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val navToGPay = binding.paymentOptionGpay
        val navToVisa = binding.paymentOptionGpay
        val navToMasterCard = binding.paymentOptionGpay
        val navToDebitCard = binding.paymentOptionGpay
        val navToPaytm = binding.paymentOptionGpay

        //navigating to next fragment
//        val navigateToCongratFragment = binding.paymentOptionBackBtn
//            navigateToCongratFragment.setOnClickListener{
//                val navController = Navigation.findNavController(requireActivity(), R.id.navigation_graph)
//                //Add destination here
//                navController.navigate(R.id.action_paymentCheckoutFragment_to_paymentDetailsFragment)
//            }

        //back arrow navigation
        val backArrow = binding.paymentOptionBackBtn
        backArrow.setOnClickListener{
            findNavController().popBackStack()
        }

        //google pay navigation
       // val navToGPay = binding.paymentOptionGpay
        navToGPay.setOnClickListener{
            goToPaystack()
            Toast.makeText(requireContext(), "Gpay Payment Received", Toast.LENGTH_SHORT).show()

//            findNavController().navigate(R.id.action_paymentCheckoutFragment_to_paymentDetailsFragment)
        }

        //Visa navigation
        //val navToVisa = binding.paymentOptionGpay
        navToVisa.setOnClickListener{
            goToPaystack()
            Toast.makeText(requireContext(), "Visa Payment Received", Toast.LENGTH_SHORT).show()
//            findNavController().navigate(R.id.action_paymentCheckoutFragment_to_paymentDetailsFragment)
        }

        //MasterCard navigation
       // val navToMasterCard = binding.paymentOptionGpay
        navToMasterCard.setOnClickListener{
            goToPaystack()
            Toast.makeText(requireContext(), "MasterCrd Payment Received", Toast.LENGTH_SHORT).show()
//            findNavController().navigate(R.id.action_paymentCheckoutFragment_to_paymentDetailsFragment)
        }

        //Debit Card navigation
       // val navToDebitCard = binding.paymentOptionGpay
        navToDebitCard.setOnClickListener{
            goToPaystack()
            Toast.makeText(requireContext(), "Debit Card Payment Received", Toast.LENGTH_SHORT).show()
//            findNavController().navigate(R.id.action_paymentCheckoutFragment_to_paymentDetailsFragment)
        }

        //Paytm navigation
       // val navToPaytm = binding.paymentOptionGpay
        navToPaytm.setOnClickListener{
            goToPaystack()
            Toast.makeText(requireContext(), "Paytm Payment Received", Toast.LENGTH_SHORT).show()
//            findNavController().navigate(R.id.action_paymentCheckoutFragment_to_paymentDetailsFragment)
        }

        onBackPressed()
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

//    private fun getBookedHotelRoomDetails() {
//        hotelViewModel.bookedHotelRoomDetails
//        hotelViewModel.bookedHotelRoomDetails.observe(viewLifecycleOwner, {
//            if (it != null) {
//                transactionReference = it.data.paymentReference
//                transactionURL = it.data.paymentUrl
//            }
//        })
//    }

//    private fun pushPaymentVerificationDetails() {
//        verificationDetails = VerifyBooking("", transactionReference)
//        hotelViewModel.pushPaymentTransactionDetails(verificationDetails!!)
//        hotelViewModel.bookingVerificationDetails.observe(viewLifecycleOwner, {
//            if (it != null) {
//                Toast.makeText(requireContext(), "Payment Successful!", Toast.LENGTH_LONG).show()
//                findNavController().navigate(R.id.action_paymentDetailsFragment_to_bookingConfirmationFragment)
//            }else {
//                Toast.makeText(requireContext(), "Unsuccessful. Please try again ", Toast.LENGTH_LONG).show()
//            }
//        })
//
//    }
    fun goToPaystack() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(transactionURL)
        startActivity(intent)
    }
    
//    fun goToConfirmedPaymentPage() {
//        val intent = Intent(Intent.ACTION_GET_CONTENT)
//        intent.data = Uri.parse("")
//        startActivity(intent)
//    }

//    private fun paymentOptionSelected() {
//        if (navToGPay.isPressed) {
//            selectedPaymentOption = navToGPay.toString()
//        }
//        if (navToVisa.isPressed) {
//            selectedPaymentOption = navToVisa.toString()
//        }
//        if (navToMasterCard.isPressed) {
//            selectedPaymentOption = navToMasterCard.toString()
//        }
//        if (navToDebitCard.isPressed) {
//            selectedPaymentOption = navToDebitCard.toString()
//        }
//        if (navToPaytm.isPressed) {
//            selectedPaymentOption = navToPaytm.toString()
//        }
//    }

}