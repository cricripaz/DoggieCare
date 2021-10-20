package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.backyardigans.doggiecare.Model.Profile
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.base.StepsBaseFragment
import com.backyardigans.doggiecare.databinding.FragmentWelcomeBioBinding
import com.backyardigans.doggiecare.databinding.FragmentWelcomeUsernameBinding
import com.backyardigans.doggiecare.viewModel.ProfileViewModel

class WelcomeUserFragment :  StepsBaseFragment() {
    private val profileViewModel: ProfileViewModel by activityViewModels()

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
        btVerificar = view.findViewById(R.id.welcomeverificar)

        btVerificar.setOnClickListener {
            val goToBio = WelcomeUserFragmentDirections.actionWelcomeUserFragmentToWelcomeBioFragment()

            profileViewModel.actualizar(Profile(binding.welcomeusuario.text.toString(),null ))


            findNavController().navigate(goToBio)
        }
    }
}