package com.example.hbapplicationgroupa.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.database.AuthPreference
import com.example.hbapplicationgroupa.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var dialog: Dialog
    private lateinit var authPreference: AuthPreference

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        authPreference = AuthPreference(requireActivity())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog = Dialog(requireContext())

        //Overriding onBack press to navigate to home Fragment onBack Pressed
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_profileFragment_to_exploreHomeFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        binding.fragmentProfileBookingsCon.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_pastBookingsFragment2)
        }

        binding.fragmentProfileHelpCon.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_helpAndSupportFragment)
        }

        binding.fragmentProfilePrivacyCon.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_privacyPolicyFragment)
        }

        binding.fragmentProfileLogoutTv.setOnClickListener {
            dialog.setContentView(R.layout.log_out_dialogbox)
            dialog.show()
            dialogActivities()
        }
    }

    //Method to logout by clearing authToken from sharedPreference
    private fun dialogActivities(){
        val logout = dialog.findViewById<TextView>(R.id.dialogLogout)
        logout.setOnClickListener {
            authPreference.clear("token_key")
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
            dialog.dismiss()
        }
    }
}