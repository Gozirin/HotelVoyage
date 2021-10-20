package com.example.hbapplicationgroupa.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.*
import com.example.hbapplicationgroupa.databinding.FragmentResetPasswordBinding
import com.example.hbapplicationgroupa.viewModel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPasswordFragment : Fragment() {
    private var _binding: FragmentResetPasswordBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AuthViewModel by viewModels()
    private val args: ResetPasswordFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        _binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        return binding.root

    }

   // @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       arguments?.let {  }
       val token = args.token
       val email = args.email


        //method to display hint a user on the password to input
        binding.tvEmailTextResetPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                binding.tvCheckEmptyNewPasswordResetPassword.text = "Must contain uppercase,lowercase letter,digit and special character"
                binding.tvCheckEmptyNewPasswordResetPassword.visibility = View.VISIBLE
            }else{
                binding.tvCheckEmptyNewPasswordResetPassword.visibility = View.INVISIBLE

            }
        }


       binding.tvConfirmPasswordResetPassword.setOnFocusChangeListener { _, hasFocus ->
           if (!hasFocus){
               binding.tvCheckEmptyConfirmPasswordResetPassword.visibility = View.INVISIBLE
           }
       }



        binding.tvResetPasswordLoginText.setOnClickListener {
            findNavController().navigate(R.id.action_resetPasswordFragment_to_loginFragment)
        }


        // button to navigate to login fragment after password reset
        binding.btnResetPassword.setOnClickListener {
            val newPassword = binding.tvEmailTextResetPassword.text.toString()
            val confirmPassword = binding.tvConfirmPasswordResetPassword.text.toString()

            if (validateNotEmptyNewPasswordField(newPassword) && validateNewPassword(newPassword)
              && validateNewPasswordAndConfirmPassword(newPassword, confirmPassword)){
                  if (token != null && email != null){
                      resetPassword(token,email, newPassword,confirmPassword)
                  }else{
                      Toast.makeText(requireContext(), "Token and email are null", Toast.LENGTH_SHORT).show()
                  }

            }
            else{

                Toast.makeText(requireContext(), "validation error", Toast.LENGTH_LONG).show()

              if (validateNotEmptyNewPasswordField(newPassword) && !validateNewPassword(newPassword)){

                    binding.tvCheckEmptyNewPasswordResetPassword.text = "Please enter valid password"
                    binding.tvCheckEmptyNewPasswordResetPassword.visibility = View.VISIBLE
                }

                if (!validateNotEmptyNewPasswordField(newPassword)){
                    binding.tvCheckEmptyNewPasswordResetPassword.text = "Please enter your new password"
                    binding.tvCheckEmptyNewPasswordResetPassword.visibility = View.VISIBLE

                }

              if (validateNotEmptyNewPasswordField(newPassword) && validateNewPassword(newPassword)
                  && !validateNewPasswordAndConfirmPassword(newPassword, confirmPassword)){
                  binding.tvCheckEmptyConfirmPasswordResetPassword.text = "Password does not match"
                  binding.tvCheckEmptyConfirmPasswordResetPassword.visibility = View.VISIBLE
              }


            }

        }
       observeResetPasswordLiveData()


      onBackPressed()
    }

    fun onBackPressed(){
        //Overriding onBack press to handle back press
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

    }

    fun resetPassword(token: String,  email: String, newPassword: String, confirmPassword: String){
        viewModel.resetUserPassword(token,email, newPassword, confirmPassword)
    }

//    // making Api call to reset user's password
    fun observeResetPasswordLiveData(){
        viewModel.resetPasswordLiveData.observe(requireActivity(),{
            if (it == null){
                Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
            }else{
                Log.d("ResetPassword 1:", it.body().toString())
                findNavController().navigate(R.id.action_resetPasswordFragment_to_loginFragment)
            }
        })
    }


}