package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.base.StepsBaseFragment

class WelcomeUserFragment :  StepsBaseFragment() {

    private lateinit var btVerificar: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_welcome_username, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btVerificar = view.findViewById(R.id.welcomeverificar)

        btVerificar.setOnClickListener {
            val goToBio = WelcomeUserFragmentDirections.actionWelcomeUserFragmentToWelcomeBioFragment()
            findNavController().navigate(goToBio)
        }
    }
}