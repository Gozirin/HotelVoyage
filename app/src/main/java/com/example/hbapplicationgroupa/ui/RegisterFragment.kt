package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.Validations.RegistrationPageValidation
import com.example.hbapplicationgroupa.connectivity.ConnectivityLiveData
import com.example.hbapplicationgroupa.databinding.FragmentRegisterBinding
import com.example.hbapplicationgroupa.model.authmodule.adduser.AddUserModel
import com.example.hbapplicationgroupa.viewModel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var connectivityLiveData: ConnectivityLiveData
    private val viewModel: AuthViewModel by viewModels()
    private lateinit var userInfo: AddUserModel

    val function = RegistrationPageValidation


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        connectivityLiveData = ConnectivityLiveData(application)
//
//        connectivityLiveData.observe(viewLifecycleOwner, {isAvailable ->
//            when(isAvailable){
//                true->{
//                    networkText.visibility = View.GONE
//                    networkImage.visibility = View.GONE
//                    postRecyclerview.visibility = View.VISIBLE
//                }
//                false->{
//                    networkText.visibility = View.VISIBLE
//                    networkImage.visibility = View.VISIBLE
//                    postRecyclerview.visibility = View.GONE
//                }
//            }
//        })


        binding.tvRegistrationTAndC.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_privacyPolicyFragment)
        }

        binding.tvRegistrationLoginText.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.btnRegister.setOnClickListener {

            binding.btnRegister.setEnabled(false)
            binding.fragmentRegisterProgressBarPb.visibility = View.VISIBLE
            val firstName = binding.fragmentRegisterFirstNameEtv.text.toString()
            val lastName = binding.fragmentRegisterLastNameEtv.text.toString()
            val email = binding.tvEmailText.text.toString()
            val password = binding.tvConfirmPasswordResetPassword.text.toString()
            val phoneNumber = binding.fragmentRegisterPhoneNumberEtv.text.toString()
            val age = if (binding.fragmentRegisterAgeEtv.text?.isEmpty() == true){
                null
            }else{
                binding.fragmentRegisterAgeEtv.text.toString().toInt()
            }

            val userName = firstName
            val gender = binding.getSpinner.selectedItem.toString()
            userInfo = AddUserModel(firstName,lastName,email, userName, password, phoneNumber, gender, age)


            if(!function.validateFirstNameInput(firstName)){
                binding.fragmentRegisterFirstNameEtv.error = "invalid input"
                binding.btnRegister.setEnabled(true)
                binding.fragmentRegisterProgressBarPb.visibility = View.GONE
            }
            if(!function.validateLastNameInput(lastName)){
                binding.fragmentRegisterLastNameEtv.error = "invalid input"
                binding.btnRegister.setEnabled(true)
                binding.fragmentRegisterProgressBarPb.visibility = View.GONE
            }
            if(!function.validateEmailInput(email)){
                binding.tvEmailText.error = "invalid input"
                binding.btnRegister.setEnabled(true)
                binding.fragmentRegisterProgressBarPb.visibility = View.GONE
            }
            if(!function.validateAgeInput(age)){
                binding.fragmentRegisterAgeEtv.error = "You must be above 18"
                binding.btnRegister.setEnabled(true)
                binding.fragmentRegisterProgressBarPb.visibility = View.GONE
            }
            if(!function.validatePhoneInput(phoneNumber)){
                binding.fragmentRegisterPhoneNumberEtv.error = "invalid input"
                binding.btnRegister.setEnabled(true)
                binding.fragmentRegisterProgressBarPb.visibility = View.GONE
            }
            if(!function.validateSexInput(gender)){
                binding.fragmentRegisterGenderErrorTv.visibility = view.visibility
                binding.btnRegister.setEnabled(true)
                binding.fragmentRegisterProgressBarPb.visibility = View.GONE
            }
            if(!function.validatePasswordInput(password)){
                binding.tvConfirmPasswordResetPassword.error = "Password too weak"
                binding.btnRegister.setEnabled(true)
                binding.fragmentRegisterProgressBarPb.visibility = View.GONE
            }
            if (!binding.RegisterTickButton.isChecked){
                binding.RegisterTickButton.error = "Accept Terms and Conditions"
                binding.btnRegister.setEnabled(true)
                binding.fragmentRegisterProgressBarPb.visibility = View.GONE
            }
            else if(function.validateFirstNameInput(firstName)
                && function.validateLastNameInput(lastName)
                && function.validateEmailInput(email)
                && function.validatePasswordInput(password)
                && function.validateAgeInput(age)
                && function.validatePhoneInput(phoneNumber)
                && function.validateSexInput(gender)){
                viewModel.addUser(userInfo)
                viewModel.addUserResponse.observe(viewLifecycleOwner,{
                    if(it.body()?.succeeded == true){
                        binding.fragmentRegisterProgressBarPb.visibility = View.GONE
                        binding.fragmentRegisterGenderErrorTv.visibility = View.GONE

                        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                        binding.fragmentRegisterPhoneNumberEtv.text?.clear()
                        binding.tvEmailText.text?.clear()
                        binding.tvConfirmPasswordResetPassword.text?.clear()
                        binding.fragmentRegisterAgeEtv.text?.clear()
                        binding.RegisterTickButton.isChecked = false
                    }else{
                        binding.btnRegister.setEnabled(true)
                        binding.fragmentRegisterProgressBarPb.visibility = View.GONE
                        binding.fragmentRegisterGenderErrorTv.visibility = View.GONE
                    }
                })

            }
        }

        onBackPressed()
    }

    fun onBackPressed(){
        //Overriding onBack press to finish activity and exit app
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        //  Created an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.gender,
            R.layout.support_simple_spinner_dropdown_item
        ).also { adapter ->

            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            // Apply the adapter to the spinner
            binding.getSpinner.adapter = adapter
            binding.getSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    p0?.getItemIdAtPosition(p2)
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                   binding.getSpinner.onItemSelectedListener = this
                }
            }
        }
    }
}