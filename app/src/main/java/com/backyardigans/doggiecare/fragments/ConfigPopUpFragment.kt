package com.backyardigans.doggiecare.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.backyardigans.doggiecare.Preferences.UserApplication.Companion.prefs
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.activities.FeedActivity
import com.backyardigans.doggiecare.activities.LoginActivity
import com.backyardigans.doggiecare.databinding.FragmentPopupConfigurationsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ConfigPopUpFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentPopupConfigurationsBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        _binding = FragmentPopupConfigurationsBinding.inflate(inflater, container, false)

        binding.popupEditarPerfil.setOnClickListener {
            //hacer con navigation
            Toast.makeText(activity, "editar perfil", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_configPopUpFragment2_to_editProfileFragment)
            //TODO mejorar el como se muestra

        }

        binding.popupSolicitarVerificacion.setOnClickListener{
            Toast.makeText(activity, "Solicitando verificacion", Toast.LENGTH_SHORT).show()

        }

        binding.popupCambiarModo.setOnClickListener {
            Toast.makeText(activity, "Cambiado a modo nocturno", Toast.LENGTH_SHORT).show()
        }

        binding.popupSalir.setOnClickListener {
            prefs.erase()

            Toast.makeText(activity, "Saliendo de la cuenta", Toast.LENGTH_SHORT).show()
            activity?.let{
                val intent = Intent (it, LoginActivity::class.java)
                it.startActivity(intent)
            }
        }

        return binding.root
    }
}