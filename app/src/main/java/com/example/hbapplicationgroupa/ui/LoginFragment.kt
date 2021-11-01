package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
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
import com.example.hbapplicationgroupa.database.AuthPreference
import com.example.hbapplicationgroupa.databinding.FragmentLoginBinding
import com.example.hbapplicationgroupa.model.authmodule.loginuser.LoginUserResponseModel
import com.example.hbapplicationgroupa.utils.*
import com.example.hbapplicationgroupa.utils.LoginValidations.enable
import com.example.hbapplicationgroupa.viewModel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

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

        loginBtn.setOnClickListener {
            if (LoginValidations.validateLoginScreen(email, password)){
                login(email.text.toString().trim(), password.text.toString().trim())
            }else{
                binding.loginErrorMsg.text = "Email and Password fields can't be empty"
            }
        }

        AuthPreference.initPreference(requireActivity())
        observeLoginAuthLiveData()

        onBackPressed()
    }

    //create function to make Login Api Call
    private fun login(email: String, password: String){
        viewModel.login(email, password)
        binding.loginProgressBar.visibility = View.VISIBLE
    }

    //set function to observe Login Live data
    private fun observeLoginAuthLiveData(){
        viewModel.getLoginAuthLiveData.observe(viewLifecycleOwner, Observer {
            when (it?.statusCode) {
                200 -> {
                    Log.d("Login-succeed", it.message)
                    binding.loginProgressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_loginFragment_to_exploreHomeFragment)
                    //Saving auth Token and Id to Shared Preference
                    AuthPreference.setToken(it.data!!.token)
                    AuthPreference.setId(it.data.id)
                    AuthPreference.setRefreshToken(it.data.refreshToken)

                    //refreshing token from api after 8 mins
                    AuthPreference.initPreference(requireActivity())
                    val token = "Bearer ${AuthPreference.getToken(AuthPreference.TOKEN_KEY)}"
                        Log.d("TOKEN", token.toString())
                    val userId = AuthPreference.getId(AuthPreference.ID_KEY)
                        Log.d("ID_KEY", userId.toString())
                    val refreshKey = AuthPreference.getRefreshToken(AuthPreference.REFRESH_KEY)
                        Log.d("REFRESH_KEY", refreshKey.toString())
                    if (userId != null) {
                        if (refreshKey != null) {
                            if (token != null) {
                                refreshTokenCountDown(token, userId, refreshKey)
                            }
                        }
                    }
                }
                400 -> {
                    binding.loginProgressBar.visibility = View.GONE
                    errorMsg.text = it.message
                    Log.d("Login400: ", it.message)
                }
                403 -> {
                    binding.loginProgressBar.visibility = View.GONE
                    errorMsg.text = it.message
                    Log.d("Login403: ", it.message)
                }
                else -> {
                    binding.loginProgressBar.visibility = View.GONE
                    errorMsg.text = it?.message
                    Log.d("Login500: ", "error")
                }
            }
        })
    }

    //Method to handle back press on this Fragment
    private fun onBackPressed(){
        //Overriding onBack press to navigate to home Fragment onBack Pressed
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    //observing refresh token live data
    private fun observeRefreshTokenLiveData(token: String, userId: String, refreshToken: String){
        try {
            viewModel.refreshToken(token, userId, refreshToken)
            viewModel.refreshTokenLiveData.observe(viewLifecycleOwner, {
                it.newJwtAccessToken?.let { newRefreshToken ->
                    AuthPreference.setToken(newRefreshToken)
                    AuthPreference.setRefreshToken(it.newRefreshToken.toString())
                Log.d("NewRefreshToken", newRefreshToken)
                }
                Log.d("REFRESH ACCESS TOKEN", it.newJwtAccessToken.toString())
                Log.d("REFRESH TOKEN", it.newRefreshToken.toString())
            })
        }catch (e: Exception){
            Log.d("REFRESHTOKEN ERROR", "Error Refreshing Token")
        }
    }

    //time countdown to refresh token
    fun refreshTokenCountDown(token: String, userId: String, refreshToken: String){
        val timer = object: CountDownTimer((8*60*1000).toLong(), 1000) {

            override fun onTick(millisUntilFinished: Long) {
                }

            override fun onFinish() {
                observeRefreshTokenLiveData(token, userId, refreshToken)
            }
        }
        timer.start()
    }
}