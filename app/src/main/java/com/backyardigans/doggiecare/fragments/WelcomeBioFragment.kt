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
import com.backyardigans.doggiecare.databinding.ActivityProfileFragmentBinding
import com.backyardigans.doggiecare.databinding.FragmentWelcomeBioBinding
import com.backyardigans.doggiecare.viewModel.ProfileViewModel

class WelcomeBioFragment :  StepsBaseFragment() {
    private val profileViewModel: ProfileViewModel by activityViewModels()

    private var _binding: FragmentWelcomeBioBinding?=null
    private val binding get() = _binding!!

    private lateinit var btListo: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBioBinding.inflate(inflater, container, false)




        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btListo = view.findViewById(R.id.welcomelisto)

        btListo.setOnClickListener {
            val goToFeed = WelcomeBioFragmentDirections.actionWelcomeBioFragmentToFeedActivity()
            profileViewModel.actualizar(Profile(null, binding.welcomebio.text.toString()))
            findNavController().navigate(goToFeed)
        }
    }
}