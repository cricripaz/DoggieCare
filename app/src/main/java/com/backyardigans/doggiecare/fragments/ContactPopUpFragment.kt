package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        binding.popupDenunciar.setOnClickListener {    //implementar?
            Toast.makeText(activity, "Denuncia enviada", Toast.LENGTH_LONG).show()
        }
        binding.popupBloquear.setOnClickListener {    //usar dialogo?
            Toast.makeText(
                activity,
                "¿Seguro que desea bloquear a este usuario?",
                Toast.LENGTH_LONG
            ).show()
        }


        return binding.root
    }

}