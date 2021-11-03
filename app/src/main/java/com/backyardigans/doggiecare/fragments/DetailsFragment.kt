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
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.firebase.firestore.FirebaseFirestore

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.detalleBotonMore.setOnClickListener {
            findNavController().navigate(R.id.action_detailsFragment_to_contactPopUpFragment)
        }
        Glide.with(requireContext()).load(arguments?.getString("urlImage")).transform(CenterCrop(), RoundedCorners(40)).error(R.drawable.ic_icon_perrito).into(binding.detalleFotoMascota)
        binding.detalleNombreMascota.text =
            arguments?.getString("animalName") + " - " + arguments?.getString("animalAge")
        binding.detalleDuenioMascota.text = arguments?.getString("userNick")
            binding.detalleUsuarioMascota.text = arguments?.getString("userMail")
        binding.detalleDescripcionMascota.text = arguments?.getString("description")
        return binding.root
    }
}