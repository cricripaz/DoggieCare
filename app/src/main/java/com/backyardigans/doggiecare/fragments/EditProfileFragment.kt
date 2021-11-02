package com.backyardigans.doggiecare.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.backyardigans.doggiecare.Preferences.UserApplication.Companion.prefs
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.databinding.FragmentEditProfileBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class EditProfileFragment : BottomSheetDialogFragment() {
    private val db = FirebaseFirestore.getInstance()
    private var _binding: FragmentEditProfileBinding?=null
    private val binding get() = _binding!!
    private var REQUEST_CODE = 0

    override fun  onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?): View {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        binding.buttonSave.setOnClickListener{
            if (binding.inputBio.text.isEmpty() or binding.inputNombre.text.isEmpty()){
                Toast.makeText(context, "Debes llenar los espacios en blanco", Toast.LENGTH_SHORT).show()

            }else{
                db.collection("users").document(prefs.getEmail()).set(
                    hashMapOf("userBio" to binding.inputBio.text.toString(), "userNick" to binding.inputNombre.text.toString() ), SetOptions.merge()
                )

                findNavController().navigate(R.id.action_editProfileFragment_to_profileFragment)
            }
        }

        /**
         //Desbloquear para publicaciones masivas
        binding.buttonSave.setOnClickListener{
            db.collection("publicaciones").document(
                Math.random().toString().substring(2, 15)).set(
                hashMapOf(
                    "animalAge" to "1 mes",
                    "animalName" to "Betto",
                    "animalBreed" to "Chupacabras",
                    "description" to "Super Larga descripcion........" +
                            "................................................." +
                            "................................................." +
                            "................................................." +
                            "................................................." +
                            "................................................." +
                            ".............................................",
                    "userNick" to prefs.getUser(),
                    "userMail" to prefs.getEmail()
                ), SetOptions.merge()
            )
        }**/
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            if (bundle.getString("camorgal").toString().equals("cam")) {
                REQUEST_CODE = 200
                takePhoto()
            } else if (bundle.getString("camorgal").toString().equals("gal")) {
                REQUEST_CODE = 100
                pickFromGallery()
            } else {
                Toast.makeText(context, "Woops! algo ha salido mal", Toast.LENGTH_SHORT).show()
            }
        }
        binding.editImageButton.setOnClickListener {
            val bundle = bundleOf("previous" to "editProfile")
            findNavController().navigate(R.id.action_editProfileFragment_to_optionsPopUpFragment, bundle)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val uri = if (REQUEST_CODE == 200) data.extras!!.get("data") else data!!.data
                    binding.editImageButton.setPadding(0, 0, 0, 0)
                    binding.editImageButton.setColorFilter(android.R.color.transparent)
                    Glide.with(requireContext()).load(uri)
                        .circleCrop()
                        .into(binding.editImageButton)
                    //todo actualizar imagen en firebase
                }
            }
            else -> {
                Toast.makeText(context, "Woops! algo ha salido mal", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun takePhoto() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CODE)
    }

    private fun pickFromGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, REQUEST_CODE)
    }
    //TODO no permitiir si no tiene internet

}