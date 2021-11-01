package com.backyardigans.doggiecare.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.backyardigans.doggiecare.Preferences.UserApplication.Companion.prefs
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.databinding.ActivityAddFragmentBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class AddFragment : Fragment() {
    private var _binding:ActivityAddFragmentBinding?=null
    private val binding get() = _binding!!
    private val db = Firebase.firestore
    private val REQUEST_CODE = 200

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = ActivityAddFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnEnviar.setOnClickListener { isTextEmpty() }
        binding.imagenupload.setOnClickListener { takePhoto() }
            }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    binding.imagenupload.setColorFilter(android.R.color.transparent)
                    binding.imagenupload.scaleType= ImageView.ScaleType.CENTER_CROP
                    Glide.with(requireContext()).load(data.extras!!.get("data"))
                        .transform(CenterCrop(),RoundedCorners(60))
                        .into(binding.imagenupload)
                }
            }
            else -> {
                Toast.makeText(context, "Woops! algo ha salido mal", Toast.LENGTH_SHORT).show()
            }
        }
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


    private fun takePhoto() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CODE)
    }
}