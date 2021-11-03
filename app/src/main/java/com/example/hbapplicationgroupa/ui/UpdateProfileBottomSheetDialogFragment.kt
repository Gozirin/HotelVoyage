package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.hbapplicationgroupa.database.AuthPreference
import com.example.hbapplicationgroupa.databinding.FragmentUpdateProfileBottomSheetDialogBinding
import com.example.hbapplicationgroupa.model.usermodule.getuserbyid.GetUserByIdResponseItem
import com.example.hbapplicationgroupa.model.usermodule.updateuserbyid.UpdateUserByIdModel
import com.example.hbapplicationgroupa.utils.UpdateProfileBottomSheetOnClickInterface
import com.example.hbapplicationgroupa.viewmodel.CustomerViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat.getDateTimeInstance
import java.util.*

@AndroidEntryPoint
class UpdateProfileBottomSheetDialogFragment(
    private val customerInfo: GetUserByIdResponseItem,
    private val updateProfileBottomSheetOnClickInterface: UpdateProfileBottomSheetOnClickInterface
) : BottomSheetDialogFragment() {
    private var _binding: FragmentUpdateProfileBottomSheetDialogBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CustomerViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentUpdateProfileBottomSheetDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val firstName = binding.updateProfileFirstNameEt.text.toString()
        val lastName = binding.updateProfileLastNameEt.text.toString()
        val phoneNumber = binding.updateProfilePhoneNumberEt.text.toString()
        val age = binding.updateProfileAgeEt.inputType
//        val ages = Integer.parseInt(age)
        val creditCard = binding.updateProfileCreditCardEt.text.toString()
        val  address = binding.updateProfileAddressEt.text.toString()
        val state = binding.updateProfileStateEt.text.toString()
        val sdf = getDateTimeInstance()
        //val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        val updatedAt = currentDate.toString()

        AuthPreference.initPreference(requireActivity())
        val authToken = "Bearer ${AuthPreference.getId(AuthPreference.TOKEN_KEY)}"
//        viewModel = ViewModelProvider(requireActivity())[CustomerViewModel::class.java]
        observeUpdateUserLiveData()

        binding.updateProfileCancelBtn.setOnClickListener {
            dismiss()
        }

        binding.updateProfileBtn.setOnClickListener {
            //I passed these values in like this because the variables above were giving me null for whatever reasons
            //I haven't handled validations
            updateUser(
                authToken,
                binding.updateProfileFirstNameEt.text.toString(),
                binding.updateProfileLastNameEt.text.toString(),
                binding.updateProfilePhoneNumberEt.text.toString(),
                binding.updateProfileAgeEt.text.toString().toInt(),
                binding.updateProfileAddressEt.text.toString(),
                binding.updateProfileCreditCardEt.text.toString(),
                binding.updateProfileStateEt.text.toString(),
                currentDate.toString()
            )

            updateProfileBottomSheetOnClickInterface.passNameToProfileFragment(
                binding.updateProfileFirstNameEt.text.toString(),
                binding.updateProfileLastNameEt.text.toString()
            )
            dismiss()
        }

        populateCustomerDetails()
    }

    private fun populateCustomerDetails(){
        binding.updateProfileFirstNameEt.setText(customerInfo.firstName)
        binding.updateProfileLastNameEt.setText(customerInfo.lastName)
        binding.updateProfilePhoneNumberEt.setText(customerInfo.phoneNumber)
        binding.updateProfileAgeEt.setText(customerInfo.age.toString())
        binding.updateProfileCreditCardEt.setText(customerInfo.creditCard)
        binding.updateProfileAddressEt.setText(customerInfo.address)
        binding.updateProfileStateEt.setText(customerInfo.state)
    }

    private fun updateUser(
        authToken: String,
        firstName: String,
        lastName: String,
        phoneNumber: String,
        age: Int,
        address: String,
        creditCard: String,
        state: String,
        updatedAt: String
    ) {
        val updateUser = UpdateUserByIdModel(firstName, lastName, phoneNumber, age, address, creditCard, state, updatedAt)
        viewModel.updateUser(authToken, updateUser)
    }
    private fun observeUpdateUserLiveData(){
        viewModel.updateUserLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null){
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}