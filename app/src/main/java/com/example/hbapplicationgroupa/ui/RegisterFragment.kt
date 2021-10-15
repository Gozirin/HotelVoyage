package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.aminography.primedatepicker.utils.visible
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.Validations.RegistrationPageValidation
import com.example.hbapplicationgroupa.connectivity.ConnectivityLiveData
import com.example.hbapplicationgroupa.databinding.FragmentRegisterBinding
import com.example.hbapplicationgroupa.model.authmodule.adduser.AddUserModel
import com.example.hbapplicationgroupa.viewmodelsss.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var connectivityLiveData: ConnectivityLiveData
    private val viewModel: AuthViewModel by viewModels()

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
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

            binding.btnRegister.visibility = View.GONE
            binding.fragmentRegisterProgressBarPb.visibility = View.VISIBLE
            //remove later
            val firstName = binding.tvRegisterUserName.text.toString()
            val lastName = binding.tvRegisterUserName.text.toString()
            val email = binding.tvEmailText.text.toString()
            val password = binding.tvConfirmPasswordResetPassword.text.toString()
            val userName = binding.tvRegisterUserName.text.toString()
            val phoneNumber = "08132247420"
            val age = 50
            val gender = "male"
            val userInfo = AddUserModel(firstName,lastName,email,userName, password, phoneNumber, gender, age)

            if(!function.validateFirstNameInput(firstName)){
                binding.tvRegisterUserName.error = "invalid input"
                binding.btnRegister.setEnabled(true)
                binding.fragmentRegisterProgressBarPb.visibility = View.GONE
                binding.btnRegister.visibility = View.VISIBLE

            }
            if(!function.validateEmailInput(email)){
                binding.tvEmailText.error = "invalid input"
                binding.btnRegister.setEnabled(true)
                binding.fragmentRegisterProgressBarPb.visibility = View.GONE
                binding.btnRegister.visibility = View.VISIBLE
            }
            if(!function.validatePasswordInput(password)){
                binding.tvConfirmPasswordResetPassword.error = "Password too weak"
                binding.btnRegister.setEnabled(true)
                binding.fragmentRegisterProgressBarPb.visibility = View.GONE
                binding.btnRegister.visibility = View.VISIBLE
            }
            if (!binding.RegisterTickButton.isChecked){
                binding.RegisterTickButton.error = "Accept Terms and Conditions"
                binding.btnRegister.setEnabled(true)
                binding.fragmentRegisterProgressBarPb.visibility = View.GONE
                binding.btnRegister.visibility = View.VISIBLE
            }
            else if(function.validateFirstNameInput(firstName) && function.validateEmailInput(email) && function.validatePasswordInput(password)){
                viewModel.addUser(userInfo)
                viewModel.addUserResponse.observe(viewLifecycleOwner,{
                    if(it.body()?.succeeded == true){
                        binding.fragmentRegisterProgressBarPb.visibility = View.GONE
                        binding.btnRegister.setEnabled(false)
                        binding.btnRegister.visibility = View.VISIBLE

//                        Toast.makeText(requireContext(), "${it.body()?.message}", Toast.LENGTH_SHORT).show()
//                        Log.d("TAG", "${it.body()?.message}")

                        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                        binding.tvRegisterUserName.text?.clear()
                        binding.tvEmailText.text?.clear()
                        binding.tvConfirmPasswordResetPassword.text?.clear()
                        binding.RegisterTickButton.isChecked = false
                    }else{
                        binding.btnRegister.setEnabled(true)
                        binding.fragmentRegisterProgressBarPb.visibility = View.GONE
                        binding.btnRegister.visibility = View.VISIBLE
//                        Toast.makeText(requireContext(), "${it.body()?.message}", Toast.LENGTH_SHORT).show()
//                        Log.d("TAG", "${it.body()?.message}")
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
    }
}