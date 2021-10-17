package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hbapplicationgroupa.R
import com.example.hbapplicationgroupa.databinding.FragmentPendingConfirmationBinding

class PendingConfirmation : Fragment() {

    private var _binding: FragmentPendingConfirmationBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPendingConfirmationBinding.inflate(inflater, container, false)
        return binding.root
    }

}