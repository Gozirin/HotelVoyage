package com.example.hbapplicationgroupa.ui

import android.content.Intent
import android.content.Intent.*
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.accessibility.AccessibilityEventCompat.setAction
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hbapplicationgroupa.MainActivity
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.database.AuthPreference
import com.example.hbapplicationgroupa.databinding.FragmentPaymentCheckoutBinding
import com.example.hbapplicationgroupa.model.hotelmodule.bookhotel.BookHotel
import com.example.hbapplicationgroupa.model.hotelmodule.bookhotel.VerifyBooking
import com.example.hbapplicationgroupa.utils.findNumberOfDays
import com.example.hbapplicationgroupa.viewModel.HotelViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.internal.operators.maybe.MaybeToPublisher.instance
import io.reactivex.internal.schedulers.TrampolineScheduler.instance
import io.reactivex.internal.util.ListAddBiConsumer.instance
import kotlin.properties.Delegates


@AndroidEntryPoint
class PaymentCheckoutFragment : Fragment() {
    //Set up view binding here
    private var _binding: FragmentPaymentCheckoutBinding? = null
    private val binding get() = _binding!!
    private val hotelViewModel: HotelViewModel by viewModels()
    private val args: PaymentCheckoutFragmentArgs by navArgs()
//    private lateinit var transactionReference: String
//    private var paymentUrl = ""
    private var price = 0.0F
    private var bookingReference = ""
    private lateinit var verificationDetails: VerifyBooking
    private lateinit var hotelBookingInfo: BookHotel
    private var selectedPaymentOption = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Enabled view binding here
        _binding = FragmentPaymentCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val checkInDate = args.checkIn
        val checkOutDate = args.checkOut
        val numberOfDays = findNumberOfDays(checkOutDate,checkInDate)
        val priceDiscount = (args.price - (args.price * 0.1))

        val price = when {
            numberOfDays >1 -> "${priceDiscount * numberOfDays}"
            else -> "${args.price * numberOfDays}"
        }
        binding.paymentOptionAmountTv.text = "N$price"
//
//        paymentUrl = args.transactionUrl
//        Log.d("XYZ", "goToPaystack: ${args.transactionUrl}")
//        Log.d("XYZ", "goToPaystack: $paymentUrl")
//
//       bookingReference = args.bookingReference


        val navToPaystack = binding.paymentOptionPaystack
//        val navToFlutterwave = binding.paymentOptionFlutterwave

        if (navToPaystack.isPressed) {
            selectedPaymentOption = binding.paymentOptionPaystackTv.text.toString()
        }
//        if (navToFlutterwave.isPressed) {
//            selectedPaymentOption = binding.paymentOptionFlutterwaveTv.text.toString()
//        }


//        val navToMasterCard = binding.paymentOptionGpay
//        val navToDebitCard = binding.paymentOptionGpay
//        val navToPaytm = binding.paymentOptionGpay

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


        //Visa navigation
        //val navToVisa = binding.paymentOptionGpay
        navToPaystack.setOnClickListener{
            pushBookHotelData()
            Toast.makeText(requireContext(), "Redirecting to Paystack", Toast.LENGTH_SHORT).show()
//            findNavController().navigate(R.id.action_paymentCheckoutFragment_to_paymentDetailsFragment)
        }


        //google pay navigation
       // val navToGPay = binding.paymentOptionGpay
//        navToFlutterwave.setOnClickListener{
//
//            Toast.makeText(requireContext(), "Redirecting to Flutterwave", Toast.LENGTH_SHORT).show()
//
//            findNavController().navigate(R.id.action_paymentCheckoutFragment_to_paymentDetailsFragment)
//        }


        //MasterCard navigation
       // val navToMasterCard = binding.paymentOptionGpay
//        navToMasterCard.setOnClickListener{
//            goToPaystack()
//            Toast.makeText(requireContext(), "MasterCrd Payment Received", Toast.LENGTH_SHORT).show()
////            findNavController().navigate(R.id.action_paymentCheckoutFragment_to_paymentDetailsFragment)
//        }

        //Debit Card navigation
       // val navToDebitCard = binding.paymentOptionGpay
//        navToDebitCard.setOnClickListener{
//            goToPaystack()
//            Toast.makeText(requireContext(), "Debit Card Payment Received", Toast.LENGTH_SHORT).show()
////            findNavController().navigate(R.id.action_paymentCheckoutFragment_to_paymentDetailsFragment)
//        }
//
//        //Paytm navigation
//       // val navToPaytm = binding.paymentOptionGpay
//        navToPaytm.setOnClickListener{
//            goToPaystack()
//            Toast.makeText(requireContext(), "Paytm Payment Received", Toast.LENGTH_SHORT).show()
////            findNavController().navigate(R.id.action_paymentCheckoutFragment_to_paymentDetailsFragment)
//        }

        onBackPressed()

//
//        val handler = Handler(Looper.getMainLooper())
//        handler.post(Runnable {
//            pushPaymentVerificationDetails()
//        })
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

    private fun pushBookHotelData() {
            val authToken = "Bearer ${AuthPreference.getToken(AuthPreference.TOKEN_KEY)}"
            hotelBookingInfo = BookHotel(
                args.roomId,
                args.checkIn,
                args.checkOut,
                args.numberOfPeople,
                "paystack"
            )
        Log.d("XYZ", "pushBookHotelData: ${args.roomId}")
        Log.d("XYZ", "pushBookHotelDatapaymentoption: $selectedPaymentOption ")
            hotelViewModel.pushBookHotel(authToken, hotelBookingInfo!!)
            hotelViewModel.bookingInfo.observe(viewLifecycleOwner, {
                if (it != null) {
                    price = it.data.price.toFloat()
//                    transactionURL = it.data.paymentUrl
                    bookingReference = it.data.paymentReference
                    Log.d("XYZ", "pushBookHotelDataroomIdpaymentreference: ${it.data.paymentReference} ")
                }
                val action = PaymentCheckoutFragmentDirections.actionPaymentCheckoutFragmentToPaymentDetailsFragment(bookingReference, price)
                findNavController().navigate(action)
            })
        }

    //direct user to web payment service
//    private fun goToPaymentUrl() {
//        val openUrlIntent = Intent()
//            .setAction(ACTION_VIEW)
//            .addCategory(CATEGORY_BROWSABLE)
//            .setData(Uri.parse(paymentUrl))
//        startActivity(openUrlIntent)
//        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(paymentUrl))
//        startActivity(intent)
    }

//    fun getPaymentUrl() {
//        hotelViewModel.getPaymentUrl()
//        hotelViewModel.paymentUrl.observe(viewLifecycleOwner, {
//            transactionURL = it.data.paymentUrl
//        })
//    }
    
//    fun goToConfirmedPaymentPage() {
//        val intent = Intent(Intent.ACTION_GET_CONTENT)
//        intent.data = Uri.parse("")
//        startActivity(intent)
//    }
