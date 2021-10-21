package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.backyardigans.doggiecare.Model.Profile
import com.backyardigans.doggiecare.Preferences.UserApplication
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.base.StepsBaseFragment
import com.backyardigans.doggiecare.databinding.ActivityProfileFragmentBinding
import com.backyardigans.doggiecare.databinding.FragmentWelcomeBioBinding
import com.backyardigans.doggiecare.viewModel.ProfileViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class WelcomeBioFragment :  StepsBaseFragment() {
    private val profileViewModel: ProfileViewModel by activityViewModels()
    private val db = FirebaseFirestore.getInstance()

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
        binding.welcomelisto.setOnClickListener {
            val goToFeed = WelcomeBioFragmentDirections.actionWelcomeBioFragmentToFeedActivity()
            db.collection("users").document(UserApplication.prefs.getEmail()).set(
                hashMapOf("userBio" to binding.welcomebio.text.toString()), SetOptions.merge()
            )

            profileViewModel.actualizar(Profile(null, binding.welcomebio.text.toString()))
            findNavController().navigate(goToFeed)
        }
    }
}