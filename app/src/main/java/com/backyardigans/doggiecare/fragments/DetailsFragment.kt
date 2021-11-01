package com.backyardigans.doggiecare.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.backyardigans.doggiecare.Preferences.UserApplication.Companion.prefs
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.detalleBotonMore.setOnClickListener {
            findNavController().navigate(R.id.action_detailsFragment_to_contactPopUpFragment)
        }

        binding.detalleNombreMascota.text =
            arguments?.getString("animalName") + " - " + arguments?.getString("animalAge")
        binding.detalleDuenioMascota.text = arguments?.getString("userNick")
        binding.detalleUsuarioMascota.text = prefs.getEmail()
        binding.detalleDescripcionMascota.text = arguments?.getString("description")

//TODO poner img

        return binding.root
    }
}