package com.example.hbapplicationgroupa.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    //Set up view binding here
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var dialog: Dialog


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Enabled view binding here
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    //TODO: UI manipulation can be done here
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog = Dialog(requireContext())

        binding.fragmentProfileBookingsCon.setOnClickListener {
//            findNavController().navigate(R.id.action_profileFragment_to_pastBookingsFragment2)
            Toast.makeText(requireContext(), "booking history clicked", Toast.LENGTH_SHORT).show()

        }
        binding.fragmentProfileHelpCon.setOnClickListener {
//            findNavController().navigate(R.id.action_profileFragment_to_helpAndSupportFragment)
            Toast.makeText(requireContext(), "help and support clicked", Toast.LENGTH_SHORT).show()

        }
        binding.fragmentProfilePrivacyCon.setOnClickListener {
            Toast.makeText(requireContext(), "privacy and policy clicked",
                Toast.LENGTH_SHORT).show()

        }
        binding.fragmentProfileLogoutTv.setOnClickListener {

//            dialog.setContentView(R.layout.log_out_dialogbox)
//            dialog.show()
            Toast.makeText(requireContext(), "logout clicked", Toast.LENGTH_SHORT).show()

        }
    }
}