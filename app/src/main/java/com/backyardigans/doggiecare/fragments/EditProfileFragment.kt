package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.backyardigans.doggiecare.Model.Profile
import com.backyardigans.doggiecare.Preferences.UserApplication
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.databinding.FragmentEditProfileBinding
import com.backyardigans.doggiecare.viewModel.ProfileViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class EditProfileFragment : BottomSheetDialogFragment() {
    private val db = FirebaseFirestore.getInstance()


    private var _binding: FragmentEditProfileBinding?=null
    private val binding get() = _binding!!
    private val profileViewModel:ProfileViewModel by activityViewModels()

    override fun  onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?): View {

        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        binding.buttonSave.setOnClickListener{
            db.collection("users").document(UserApplication.prefs.getEmail()).set(
                hashMapOf("userBio" to binding.inputBio.text.toString(), "userNick" to binding.inputNombre.text.toString() ), SetOptions.merge()
            )

            profileViewModel.actualizar(Profile(binding.inputNombre.text.toString(), binding.inputBio.text.toString()))
            findNavController().navigate(R.id.action_editProfileFragment_to_profileFragment)

        }


        binding.editImageButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Toast.makeText(context, "Woops! todavía no puedes cambiar tu foto de perfil", Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }


}