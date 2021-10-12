package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.ResetPassword
import com.example.hbapplicationgroupa.databinding.FragmentResetPasswordBinding
import com.google.android.material.textfield.TextInputEditText

class ResetPasswordFragment : Fragment() {
    private var _binding: FragmentResetPasswordBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        private lateinit var bind : TextInputEditText
//        private lateinit var get : TextInputEditText

       val bind = binding.tvEmailTextResetPassword.text.toString()
        val get = binding.tvConfirmPasswordResetPassword.text.toString()

        binding.tvResetPasswordLoginText.setOnClickListener {
            findNavController().navigate(R.id.action_resetPasswordFragment_to_loginFragment)
        }

        binding.btnResetPassword.setOnClickListener {

//            if (ResetPassword.validatePassword(binding.tvEmailTextResetPassword, binding.tvConfirmPasswordResetPassword)){
//                findNavController().navigate(R.id.action_resetPasswordFragment_to_loginFragment)
//            }else{
//                Toast.makeText(requireContext(), "Fields can't be empty", Toast.LENGTH_SHORT).show()
//            }

            if (ResetPassword.validateField(bind, get)) {
                findNavController().navigate(R.id.action_resetPasswordFragment_to_loginFragment)
            } else {
                Toast.makeText(context,ResetPassword.errorMessage,Toast.LENGTH_SHORT).show()
//                ResetPassword.errorMessage
            }


        }

    }
}