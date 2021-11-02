package com.example.hbapplicationgroupa.ui

import android.icu.util.Calendar
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import co.paystack.android.Paystack
import co.paystack.android.PaystackSdk
import co.paystack.android.PaystackSdk.applicationContext
import co.paystack.android.Transaction
import co.paystack.android.exceptions.ExpiredAccessCodeException
import co.paystack.android.model.Card
import co.paystack.android.model.Charge
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.databinding.FragmentBookingDetailsBinding
import com.example.hbapplicationgroupa.databinding.FragmentPaymentDetailsBinding
import com.example.hbapplicationgroupa.model.hotelmodule.bookhotel.VerifyBooking
import com.example.hbapplicationgroupa.utils.ConnectivityLiveData
import com.example.hbapplicationgroupa.utils.CreditCardFormatter
import com.example.hbapplicationgroupa.utils.Resources
import com.example.hbapplicationgroupa.viewModel.HotelViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.platform.Jdk9Platform.Companion.isAvailable
import org.json.JSONException

@AndroidEntryPoint
class PaymentDetailsFragment : Fragment() {
    private var _binding: FragmentPaymentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var connectivityLiveData: ConnectivityLiveData
    private lateinit var connectivityManager: ConnectivityManager
    private var transaction: Transaction? = null
    private var transactionReference: String? = null
    private var charge: Charge? = null
//    private val args: PaymentCheckoutFragmentArgs by navArgs()
    private val hotelViewModel: HotelViewModel by viewModels()
    private var verificationDetails: VerifyBooking? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentDetailsBinding.inflate(inflater, container, false)
        return binding.root

        //Initialize Paystack
//        val handler = Handler(Looper.getMainLooper())
//        handler.post() {
//            PaystackSdk.initialize(PaystackSdk.applicationContext)
//        }


    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        connectivityLiveData = ConnectivityLiveData(connectivityManager)

        addTextWatcherToEditTextEntries()
        handleOnSubmitClick()
       // pushPaymentVerificationDetails()

    }

    private fun addTextWatcherToEditTextEntries() {

        //make button unclickable on create
        binding.paymentDetailsSubmitButton.isEnabled = false
        binding.paymentDetailsSubmitButton.background = ContextCompat.getDrawable(requireContext(), R.drawable.btn_round_opaque)
        
        //make button clickable on changes in edit text field
        val watcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {
                val card = binding.paymentDetailsCardNumberEt.text.toString()
                val expiry = binding.paymentDetailsExpiryEt.text.toString()
                val cvv = binding.paymentDetailsCvvEt.text.toString()

                //check if edit text fields are empty. If so, make submit button unclickable.
                if (card.isEmpty() || expiry.isEmpty() || cvv.isEmpty()) {
                    binding.paymentDetailsSubmitButton.isEnabled = false
                    binding.paymentDetailsSubmitButton.background = ContextCompat.getDrawable(requireContext(), R.drawable.btn_round_opaque)
                }

                //validate inputs in edit text field
                //card number must be 16 or more digits
                //expiry must be of length 5
                //cvv must be of length 3
                if (card.length >= 16 && expiry.length == 5 && cvv.length == 3) {
                    binding.paymentDetailsSubmitButton.isEnabled = true
                    binding.paymentDetailsSubmitButton.background = ContextCompat.getDrawable(requireContext(), R.drawable.btn_border_blue_bg)
                } else {
                    binding.paymentDetailsSubmitButton.isEnabled = false
                    binding.paymentDetailsSubmitButton.background = ContextCompat.getDrawable(requireContext(), R.drawable.btn_round_opaque)
                }

                //add a slash after the first two characters of expiry date
                if (expiry.length == 2) {
                    if (start == 2 && before == 1 && !expiry.contains("/")) {
                        binding.paymentDetailsExpiryEt.setText(" ")
                        binding.paymentDetailsExpiryEt.setSelection(1)
                    }else {
                        binding.paymentDetailsExpiryEt.setText(getString(R.string.expiry_forwardSlash))
                        binding.paymentDetailsExpiryEt.setSelection(3)
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        }

        //add the text watcher to the edit text views
        binding.paymentDetailsCardNumberEt.addTextChangedListener(CreditCardFormatter())    //helper class for card number formatting
        binding.paymentDetailsExpiryEt.addTextChangedListener(watcher)
        binding.paymentDetailsCvvEt.addTextChangedListener(watcher)

    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun handleOnSubmitClick() {
        //on click of the pay button
        binding.paymentDetailsSubmitButton.setOnClickListener {
            //show loading
            binding.paymentDetailsPaymentOrderProgress.visibility = View.VISIBLE
            binding.paymentDetailsSubmitButton.visibility = View.GONE

            //first, check for network availability
            connectivityLiveData.observe(viewLifecycleOwner, {
                when(isAvailable) {
                    true -> {
                        //process payment
                        executePayment()
                    }
                    false -> {
                        Toast.makeText(requireContext(), "Network error. Please, check your network connection", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun executePayment() {
        val publicTestKey = "pk_test_515f61d7b258bbc0cfcd1367e2a62c1ca5d267b0"

        //set public key
        PaystackSdk.setPublicKey(publicTestKey)

        //initialise the charge
        charge = Charge()
        charge!!.card = loadCardFromPaymentDetailsForm()
        charge!!.amount = 7999
        charge!!.email = "myemail@jimdo.com"
        charge!!.reference = "payment" + Calendar.getInstance().timeInMillis

        try {
            charge!!.putCustomField("Charged From", "Android SDK")
        }catch (e: JSONException) {
            e.printStackTrace()
        }
        chargeCardFunction()
    }

    private fun loadCardFromPaymentDetailsForm(): Card {
        //validate inputs from edit text
        val cardNumber = binding.paymentDetailsCardNumberEt.text.toString().trim()
        val expiryDate = binding.paymentDetailsExpiryEt.text.toString().trim()
        val cvv = binding.paymentDetailsCvvEt.text.toString().trim()

        //format input values
        val cardNumberWithNoSpace = cardNumber.replace(" ", "")
        val monthValue = expiryDate.substring(expiryDate.length.coerceAtMost(2))
        val yearValue = expiryDate.takeLast(2)

        //build card object with formatted number ONLY. others fields will be updated thereafter
        val card: Card = Card.Builder(cardNumberWithNoSpace, 0, 0, "").build()

        //include the cvc field of the card
        card.cvc = cvv

        //validate expiry month
        val expMonth: String = monthValue
        var month = 0
        try {
            month = expMonth.toInt()
        }catch (ignored: Exception) {

        }
        card.expiryMonth = month

        //validate expiry year
        val expYear: String = yearValue
        var year = 0
        try {
            year = expYear.toInt()
        }catch (ignored: java.lang.Exception) {

        }
        card.expiryYear = year

        return card
    }


    //charge card and handle callbacks
    private fun chargeCardFunction() {
        transaction = null

        PaystackSdk.chargeCard(requireActivity(), charge, object : Paystack.TransactionCallback {
            //on successful validation...
            override fun onSuccess(transaction: Transaction?) {
                binding.paymentDetailsPaymentOrderProgress.visibility = View.GONE
                binding.paymentDetailsSubmitButton.visibility = View.VISIBLE
                this@PaymentDetailsFragment.transaction = transaction

                //store transaction reference and perform verification at the backend server
                 transactionReference = transaction?.reference
            }

            // This is called only before requesting OTP
            // Save reference so you may send to server if
            // error occurs with OTP
            // No need to dismiss dialog
            override fun beforeValidate(transaction: Transaction?) {
               this@PaymentDetailsFragment.transaction = transaction
            }

            override fun onError(error: Throwable?, transaction: Transaction?) {
                binding.paymentDetailsPaymentOrderProgress.visibility = View.GONE
                binding.paymentDetailsSubmitButton.visibility = View.VISIBLE

                // If an access code has expired, simply ask your server for a new one
                // and restart the charge instead of displaying error

                this@PaymentDetailsFragment.transaction = transaction
                when {
                    error is ExpiredAccessCodeException -> {
                        this@PaymentDetailsFragment.chargeCardFunction()
                        return
                    }
                    transaction!!.reference != null -> {

                        Toast.makeText(requireContext(), error!!.message?:"", Toast.LENGTH_LONG).show()

                        //also you can do a verification on your backend server here

                    }
                    else -> {

                        Toast.makeText(requireContext(), error!!.message?:"", Toast.LENGTH_LONG).show()

                    }
                }
            }

        })
    }



    override fun onPause() {
        super.onPause()
        binding.paymentDetailsPaymentOrderProgress.visibility = View.GONE
    }


}