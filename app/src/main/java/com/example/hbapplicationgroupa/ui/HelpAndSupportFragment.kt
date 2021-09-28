package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.databinding.FragmentHelpAndSupportBinding

class HelpAndSupportFragment : Fragment() {
    //Set up view binding here
    private var _binding: FragmentHelpAndSupportBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Enabled view binding here
        _binding = FragmentHelpAndSupportBinding.inflate(inflater, container, false)
        return binding.root
    }

    //TODO: UI manipulation can be done here
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.fragmentHelpBackwardIv.setOnClickListener{
            findNavController().navigate(R.id.action_helpAndSupportFragment_to_profileFragment)
        }
        binding.fragmentHelpSendInquiryBtn.setOnClickListener {
            var email = binding.fragmentHelpEmailEt.text.toString()
            var title = binding.fragmentHelpInquiryTitleEt.text.toString()
            var inquiry = binding.fragmentHelpInquiryEt.text.toString()

            if (email.isEmpty() || title.isEmpty() || inquiry.isEmpty()){
                Toast.makeText(context, "Please, fill the empty spaces to ensure proper details",
                    Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, "Thanks for the info",
                    Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_helpAndSupportFragment_to_profileFragment)
            }
        }
    }
}