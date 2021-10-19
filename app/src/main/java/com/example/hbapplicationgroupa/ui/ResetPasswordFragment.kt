package com.example.hbapplicationgroupa.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavHost
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
    private lateinit var viewModel: AuthViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val token = arguments?.getString("token")
        val email = arguments?.getString("email")
        val newPassword = binding.tvEmailTextResetPassword.text.toString()
        val confirmPassword = binding.tvConfirmPasswordResetPassword.text.toString()

        //method to display hint a user on the password to input
        binding.tvEmailTextResetPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                binding.tvCheckEmptyNewPasswordResetPassword.text = "password should contain at least one uppercase letter digit special character"
                binding.tvCheckEmptyNewPasswordResetPassword.visibility = View.VISIBLE
            }else{
                binding.tvCheckEmptyNewPasswordResetPassword.visibility = View.GONE
            }
        }

        binding.tvResetPasswordLoginText.setOnClickListener {
            findNavController().navigate(R.id.action_resetPasswordFragment_to_loginFragment)
        }
        // button to navigate to login fragment after password reset
        binding.btnResetPassword.setOnClickListener {

          if (validateNotEmptyNewPasswordField(newPassword) && validateNotEmptyConfirmPasswordField(confirmPassword) &&
                validateNewPassword(newPassword) &&
                validateNewPasswordAndConfirmPassword(newPassword, confirmPassword)){
               makeApiCall(token!!,email!!, newPassword,confirmPassword)
            }else{

                if (!validateNotEmptyNewPasswordField(newPassword)){
                    binding.tvCheckEmptyNewPasswordResetPassword.text = "please enter your new password"
                    binding.tvCheckEmptyNewPasswordResetPassword.visibility = View.VISIBLE
                }
                if(!validateNotEmptyConfirmPasswordField(confirmPassword) && binding.tvConfirmPasswordResetPassword.hasFocus()){
                    binding.tvCheckEmptyConfirmPasswordResetPassword.visibility = View.VISIBLE
                }

                if (!validateNewPassword(newPassword)){
                    binding.tvCheckEmptyNewPasswordResetPassword.text = "enter valid password"
                    binding.tvCheckEmptyNewPasswordResetPassword.visibility = View.VISIBLE
                }

                if (!validateNewPasswordAndConfirmPassword(newPassword,confirmPassword)){
                    binding.tvCheckEmptyConfirmPasswordResetPassword.text = "password does not match"
                    binding.tvCheckEmptyConfirmPasswordResetPassword.visibility = View.VISIBLE

                }

            }

        }
        binding


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


    // making Api call to reset user's password
    fun makeApiCall( token: String,  email: String, newPassword: String, confirmPassword: String){
        viewModel.resetPasswordLiveData.observe(requireActivity(),{
            if (it == null){
                Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
            }else{
                findNavController().navigate(R.id.action_resetPasswordFragment_to_loginFragment)
            }
        })
        viewModel.resetUserPassword(token,email, newPassword, confirmPassword)
    }



}