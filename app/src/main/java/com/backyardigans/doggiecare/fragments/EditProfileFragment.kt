package com.backyardigans.doggiecare.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
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
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class EditProfileFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentEditProfileBinding?=null
    private val binding get() = _binding!!
    private val db = Firebase.firestore
    private var REQUEST_CODE = 0
    val GALLERY_REQUEST_CODE = 2
    val storage = Firebase.storage
    var imageUrl =""

    override fun  onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?): View {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        binding.buttonSave.setOnClickListener{
            if (binding.inputBio.text.isEmpty() or binding.inputNombre.text.isEmpty() || imageUrl==""){
                Toast.makeText(context, "Debes llenar los espacios en blanco", Toast.LENGTH_SHORT).show()

            }else{


                        db.collection("users").document(prefs.getEmail()).set(

                            hashMapOf(
                            "userBio" to binding.inputBio.text.toString(),
                            "userNick" to binding.inputNombre.text.toString(),
                            "userPic" to imageUrl), SetOptions.merge())




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
                selectImageFromGallery()
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
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            binding.editImageButton.setPadding(0, 0, 0, 0)
            binding.editImageButton.setColorFilter(android.R.color.transparent)
            binding.editImageButton.scaleType = ImageView.ScaleType.CENTER_CROP
            val imageUri = data.extras!!.get("data") as Bitmap//data.extras!!.get("data") as Bitmap
            val file = createImageFile()
            if (file != null) {
                val fout: FileOutputStream
                try {
                    fout = FileOutputStream(file)
                    imageUri.compress(Bitmap.CompressFormat.PNG, 70, fout)
                    fout.flush()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                val uri = Uri.fromFile(file)
                uploadImageToFirebase(uri)
            }
            Glide.with(requireContext()).load(imageUri)
                .circleCrop()
                .into(binding.editImageButton)
        }
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null && data.data != null){
            binding.editImageButton.setPadding(0, 0, 0, 0)
            binding.editImageButton.setColorFilter(android.R.color.transparent)
            binding.editImageButton.scaleType = ImageView.ScaleType.CENTER_CROP
            val file_uri = data.data
            Glide.with(requireContext()).load(file_uri)
                .circleCrop()
                .into(binding.editImageButton)
            if (file_uri != null) {
                uploadImageToFirebase(file_uri) } } }

    fun createImageFile(): File? {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        var mFileTemp: File? = null
        val root = requireActivity().getDir("my_sub_dir", Context.MODE_PRIVATE).absolutePath
        val myDir = File("$root/Img")
        if (!myDir.exists()) {
            myDir.mkdirs()
        }
        try {
            mFileTemp = File.createTempFile(imageFileName, ".jpg", myDir.absoluteFile)
        } catch (e1: IOException) {
            e1.printStackTrace()
        }
        return mFileTemp
    }

    private fun takePhoto() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CODE) }

    private fun selectImageFromGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Por favor, elija una imagen"),GALLERY_REQUEST_CODE) }

    private fun uploadImageToFirebase(fileUri: Uri) {
        val fileName = UUID.randomUUID().toString() +".jpg"
        val refStorage = storage.reference.child("users/$fileName")
        refStorage.putFile(fileUri)
            .addOnSuccessListener(
                OnSuccessListener<UploadTask.TaskSnapshot> { taskSnapshot ->
                    taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                        imageUrl = it.toString() }})
            ?.addOnFailureListener(OnFailureListener { e ->
                Toast.makeText(activity, "Woops! Parece que algo salió mal", Toast.LENGTH_SHORT).show()  })
    }
}