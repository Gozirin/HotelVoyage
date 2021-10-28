package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hbapplicationgroupa.database.AuthPreference
import com.example.hbapplicationgroupa.databinding.FragmentUpdateProfileBottomSheetDialogBinding
import com.example.hbapplicationgroupa.model.usermodule.updateuserbyid.UpdateUserByIdModel
import com.example.hbapplicationgroupa.viewmodel.CustomerViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat.getDateInstance
import java.text.DateFormat.getDateTimeInstance
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class UpdateProfileBottomSheetDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentUpdateProfileBottomSheetDialogBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: CustomerViewModel

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
        viewModel = ViewModelProvider(requireActivity())[CustomerViewModel::class.java]
        observeUpdateUserLiveData()

        binding.updateProfileBtn.setOnClickListener {
            updateUser(authToken, binding.updateProfileFirstNameEt.text.toString(), binding.updateProfileLastNameEt.text.toString(), binding.updateProfilePhoneNumberEt.text.toString(), binding.updateProfileAgeEt.text.toString().toInt(), binding.updateProfileAddressEt.text.toString(), binding.updateProfileCreditCardEt.text.toString(), binding.updateProfileStateEt.text.toString(), currentDate.toString())
        }
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