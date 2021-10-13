package com.example.hbapplicationgroupa.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.room.RoomOpenHelper
import com.example.hbapplicationgroupa.*
import com.example.hbapplicationgroupa.databinding.FragmentResetPasswordBinding
import com.example.hbapplicationgroupa.model.authmodule.resetpassword.ResetPasswordModel
import com.example.hbapplicationgroupa.viewmodelsss.AuthViewModel
import com.google.android.material.textfield.TextInputEditText
import java.io.ObjectInputValidation
import java.util.regex.Pattern
import javax.xml.validation.ValidatorHandler

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


        binding.tvEmailTextResetPassword.setOnFocusChangeListener { view, hasFocus ->
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
            val newPassword = binding.tvEmailTextResetPassword.text.toString()
            val confirmPassword = binding.tvConfirmPasswordResetPassword.text.toString()


            if (validateNotEmptyNewPasswordField(newPassword) &&
                validateNotEmptyConfirmPasswordField(confirmPassword) &&
                validateNewPassword(newPassword) &&
                validateNewPasswordAndConfirmPassword(newPassword, confirmPassword)){
                findNavController().navigate(R.id.action_resetPasswordFragment_to_loginFragment)
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

    }


    // making Api call to reset user's password
    fun makeApiCall(email: String, token: String, newPassword: String, confirmPassword: String){
        viewModel.resetPasswordLiveData.observe(requireActivity(),{
            if (it == null){

            }else{

            }
        })
        viewModel.resetUserPassword(email,token, newPassword, confirmPassword)
    }



}