package com.example.hbapplicationgroupa.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hbapplicationgroupa.R
import androidx.room.RoomOpenHelper
import com.example.hbapplicationgroupa.*
import com.example.hbapplicationgroupa.databinding.FragmentResetPasswordBinding
import com.example.hbapplicationgroupa.model.authmodule.resetpassword.ResetPasswordModel
import com.example.hbapplicationgroupa.viewmodel.AuthViewModel
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import java.io.ObjectInputValidation
import java.util.regex.Pattern
import javax.xml.validation.ValidatorHandler

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

   // @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




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
            val token = arguments?.getString("token")
            val email = arguments?.getString("email")

            if (validateNotEmptyNewPasswordField(newPassword) && validateNewPassword(newPassword)
              && validateNewPasswordAndConfirmPassword(newPassword, confirmPassword)){

                  makeApiCall(token!!,email!!, newPassword,confirmPassword)
            }
            else{

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


//    // making Api call to reset user's password
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