package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.backyardigans.doggiecare.Preferences.UserApplication.Companion.prefs
import com.backyardigans.doggiecare.base.StepsBaseFragment
import com.backyardigans.doggiecare.databinding.FragmentWelcomeUsernameBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class WelcomeUserFragment :  StepsBaseFragment() {
    private val db = FirebaseFirestore.getInstance()
    private var _binding: FragmentWelcomeUsernameBinding?=null
    private val binding get() = _binding!!
    private lateinit var btVerificar: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeUsernameBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       binding.welcomeverificar.setOnClickListener {
            val goToBio = WelcomeUserFragmentDirections.actionWelcomeUserFragmentToWelcomeBioFragment()

           db.collection("users").document(prefs.getEmail()).set(
               hashMapOf("userNick" to binding.welcomeusuario.text.toString()), SetOptions.merge()
           )
           prefs.saveUser(binding.welcomeusuario.text.toString())
            findNavController().navigate(goToBio)
        }
    }
}