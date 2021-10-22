package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.databinding.FragmentEmailComfirmationBinding
import com.example.hbapplicationgroupa.viewModel.AuthViewModel

class EmailConfirmation : Fragment() {

    private var _binding: FragmentEmailComfirmationBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AuthViewModel
    private val args: EmailConfirmationArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentEmailComfirmationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val token = args.token
        val email = args.email
        viewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]
        binding.confirmationBtn.setOnClickListener {
            if (email != null && token != null) {
                confirmEmail(email, token)
            }
        }

        observeConfirmEmailLiveData()
    }

    fun confirmEmail (email: String, token: String){
        viewModel.confirmEmail(email, token)
    }

    fun observeConfirmEmailLiveData(){
        viewModel.getConfirmEmailLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null){
                findNavController().navigate(R.id.action_emailConfirmation_to_loginFragment)
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "Confirmation Failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

}