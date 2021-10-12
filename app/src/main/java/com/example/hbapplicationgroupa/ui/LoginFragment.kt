package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.databinding.FragmentLoginBinding
import com.example.hbapplicationgroupa.utils.LoginValidations
import com.example.hbapplicationgroupa.utils.LoginValidations.enable
import com.example.hbapplicationgroupa.viewmodelsss.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var errorMsg: TextView

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        errorMsg = binding.loginErrorMsg

        binding.tvForgotPasswordText.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment2)
        }

        binding.tvLoginRegisterText.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        val email = binding.tvEmailTeLoginScreen
        val password = binding.tvEditPasswordLoginScreen
        val loginBtn = binding.btnLoginScreen

        loginBtn.enable(false)

        LoginValidations.validateLoginFields(email, password, loginBtn)

        loginBtn.setOnClickListener {
            observeLoginAuthLiveData(email.text.toString().trim(), password.text.toString().trim())
        }

        onBackPressed()
    }

    private fun observeLoginAuthLiveData(email: String, password: String){
        viewModel.getLoginAuthLiveData.observe(viewLifecycleOwner, Observer {
            if (it.isSuccessful && it != null){
                Toast.makeText(requireContext(), it.message(), Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_loginFragment_to_exploreHomeFragment)
            }else{
                errorMsg.text = viewModel.loginErrorMsg
            }
        })
        viewModel.login(email, password)
    }

    //Method to handle back press
    private fun onBackPressed(){
        //Overriding onBack press to navigate to home Fragment onBack Pressed
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }
}