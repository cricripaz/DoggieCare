package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.databinding.FragmentPopupContactMenuBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ContactPopUpFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentPopupContactMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopupContactMenuBinding.inflate(inflater, container, false)
        binding.popupVerPerfil.setOnClickListener {
            findNavController().navigate(R.id.action_contactPopUpFragment_to_profileFragment)
        }

        binding.popupContactar.setOnClickListener {
            findNavController().navigate(R.id.action_contactPopUpFragment_to_chatFragment)
        }

        binding.popupDenunciar.setOnClickListener {
            val bundle = bundleOf("previous" to "report")
            findNavController().navigate(
                R.id.action_contactPopUpFragment_to_optionsPopUpFragment,
                bundle
            )

        }
        binding.popupBloquear.setOnClickListener {
            val bundle = bundleOf("previous" to "block")
            findNavController().navigate(
                R.id.action_contactPopUpFragment_to_optionsPopUpFragment,
                bundle
            )
        }

        binding.popupnombredueno.text = arguments?.getString("userNick")
        binding.popupusuario.text = arguments?.getString("userMail")
        return binding.root
    }

}