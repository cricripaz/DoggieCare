package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.databinding.FragmentPopupOptionsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OptionsPopUpFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentPopupOptionsBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        _binding = FragmentPopupOptionsBinding.inflate(inflater, container, false)

        binding.popupoptionsEliminar.setOnClickListener {
            Toast.makeText(activity, "Publicación eliminada", Toast.LENGTH_SHORT).show()
        }
        binding.popupoptionsEditar.setOnClickListener {
            findNavController().navigate(R.id.action_optionsPopUpFragment_to_addFragment)
        }


        return binding.root
    }
}