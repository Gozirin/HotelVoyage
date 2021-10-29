package com.example.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hbapplicationgroupa.databinding.FragmentUpdateProfileBottomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class UpdateProfileBottomSheetDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentUpdateProfileBottomSheetDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentUpdateProfileBottomSheetDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}