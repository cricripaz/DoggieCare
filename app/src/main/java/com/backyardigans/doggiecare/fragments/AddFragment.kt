package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.backyardigans.doggiecare.Model.Feed
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.data.TemptDataSource
import com.backyardigans.doggiecare.databinding.ActivityAddFragmentBinding


class AddFragment : Fragment() {
    private var _binding:ActivityAddFragmentBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = ActivityAddFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnEnviar.setOnClickListener { textEmpty() }
    }

    private fun textEmpty() {

        if (binding.etDescripcion.text.isEmpty() || binding.etEdad.text.isEmpty() ||
            binding.etNombre.text.isEmpty() || binding.etRaza.text.isEmpty()
        ) {
            Toast.makeText(activity, "Necesita llenar todo", Toast.LENGTH_SHORT).show()
        } else {
            TemptDataSource.postList.add(Feed(
                    R.drawable.ic_img_dog, "Mesa Cocien", "LaRepetidora",
                binding.etNombre.text.toString(), binding.etEdad.text.toString(),""
            ))
            Toast.makeText(activity, "Agregado", Toast.LENGTH_SHORT).show()
//TODO hacer que vaya a la pagina principal luego de agregarlo
        }
    }
}