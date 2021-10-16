package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.backyardigans.doggiecare.Preferences.UserApplication.Companion.prefs
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.databinding.ActivityAddFragmentBinding
import com.backyardigans.doggiecare.databinding.FragmentPopupConfigurationsBinding

class ConfigPopUpFragment : Fragment() {
    private var _binding: FragmentPopupConfigurationsBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        _binding = FragmentPopupConfigurationsBinding.inflate(inflater, container, false)

        binding.popupEditarPerfil.setOnClickListener {
            //hacer con navigation
            Toast.makeText(activity, "editar perfil", Toast.LENGTH_SHORT).show()
        }

        binding.popupSolicitarVerificacion.setOnClickListener {
            Toast.makeText(activity, "Su solicitud será procesada por nuestro equipo", Toast.LENGTH_SHORT).show()
        }

        binding.popupCambiarModo.setOnClickListener {
            Toast.makeText(activity, "Cambiado a modo nocturno", Toast.LENGTH_SHORT).show()
        }

        binding.popupSalir.setOnClickListener {
            prefs.erase()
            Toast.makeText(activity, "Saliendo de la cuenta", Toast.LENGTH_SHORT).show()
        }


        return binding.root
    }
}