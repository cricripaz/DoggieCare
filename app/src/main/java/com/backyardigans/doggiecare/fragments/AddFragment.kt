package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.backyardigans.doggiecare.Model.Feed
import com.backyardigans.doggiecare.Preferences.UserApplication
import com.backyardigans.doggiecare.Preferences.UserApplication.Companion.prefs
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.data.TemptDataSource
import com.backyardigans.doggiecare.databinding.ActivityAddFragmentBinding
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class AddFragment : Fragment() {
    private var _binding:ActivityAddFragmentBinding?=null
    private val binding get() = _binding!!
    private val db = Firebase.firestore

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = ActivityAddFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnEnviar.setOnClickListener { isTextEmpty() }
    }

    private fun isTextEmpty() {

        if (binding.etDescripcion.text.isEmpty() || binding.etEdad.text.isEmpty() ||
            binding.etNombre.text.isEmpty() || binding.etRaza.text.isEmpty()
        ) {
            Toast.makeText(activity, "Necesita llenar todo", Toast.LENGTH_SHORT).show()
        } else {

            val data = hashMapOf(
                "animalAge" to binding.etEdad.text.toString(),
                "animalName" to binding.etNombre.text.toString(),
                "animalBreed" to binding.etRaza.text.toString(),
                "description" to binding.etDescripcion.text.toString(),
                "userNick" to prefs.getUser(),
                "userMail" to prefs.getEmail()
            )

            db.collection("publicaciones").document(
                prefs.getEmail()+Math.random().toString().substring(2, 4)).set(data)
            Toast.makeText(activity, "Agregado", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment_to_homeFragment)



        }
    }
}