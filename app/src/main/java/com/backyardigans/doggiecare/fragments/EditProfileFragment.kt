package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.backyardigans.doggiecare.Model.Profile
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.databinding.ActivityAddFragmentBinding
import com.backyardigans.doggiecare.databinding.FragmentEditProfileBinding
import com.backyardigans.doggiecare.viewModel.ProfileViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EditProfileFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentEditProfileBinding?=null
    private val binding get() = _binding!!
    private val profileViewModel: ProfileViewModel by viewModels()
    override fun  onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?): View {

        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)

        binding.buttonSave.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {
                profileViewModel.actualizar(Profile(binding.inputNombre.text.toString(), binding.inputBio.text.toString()))
            }
        })

        binding.editImageButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Toast.makeText(context, "Woops! todavía no puedes cambiar tu foto de perfil", Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }


}