package com.backyardigans.doggiecare.fragments

import android.annotation.SuppressLint
import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import android.os.Environment;
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.backyardigans.doggiecare.Preferences.UserApplication.Companion.prefs
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.databinding.ActivityAddFragmentBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import java.io.*
import java.util.*

class AddFragment : Fragment() {
    private var _binding: ActivityAddFragmentBinding? = null
    private val binding get() = _binding!!
    private val db = Firebase.firestore
    private var REQUEST_CODE = 0
    val GALLERY_REQUEST_CODE = 2

    val storage = Firebase.storage
    var storageRef = storage.reference

    var imageUrl = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActivityAddFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("UseRequireInsteadOfGet")
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
        binding.btnEnviar.setOnClickListener { isTextEmpty() }
        binding.imagenupload.setOnClickListener {
            val bundle = bundleOf("previous" to "add")
            findNavController().navigate(
                R.id.action_addFragment_to_optionsPopUpFragment,
                bundle
            )

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


                if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
                    binding.imagenupload.setPadding(0, 0, 0, 0)
                    binding.imagenupload.setColorFilter(android.R.color.transparent)
                    binding.imagenupload.scaleType = ImageView.ScaleType.CENTER_CROP


                        Glide.with(requireContext()).load(data.extras!!.get("data") as Bitmap)
                            .transform(CenterCrop(), RoundedCorners(60))
                            .into(binding.imagenupload)






//
//                    Uri.fromFile(bitmapToFile(data.extras!!.get("data") as Bitmap)
//
//                    uploadImageToFirebase(uri)



                }

                        if (requestCode == GALLERY_REQUEST_CODE
            && resultCode == Activity.RESULT_OK
            && data != null
            && data.data != null
        ){
            binding.imagenupload.setPadding(0, 0, 0, 0)
            binding.imagenupload.setColorFilter(android.R.color.transparent)
            binding.imagenupload.scaleType = ImageView.ScaleType.CENTER_CROP

            if (data != null) {
                val file_uri = data.data

                Glide.with(requireContext()).load(file_uri)
                    .transform(CenterCrop(), RoundedCorners(60))
                    .into(binding.imagenupload)
                if (file_uri != null) {
                    uploadImageToFirebase(file_uri)
                }

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
                "userMail" to prefs.getEmail(),
                "created" to FieldValue.serverTimestamp()//firebase.database.ServerValue.TIMESTAMP
            )

            db.collection("publicaciones").document(
                prefs.getEmail() + Math.random().toString().substring(2, 4)
            ).set(data)
            Toast.makeText(activity, "Agregado", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_homeFragment)
        }
    }

    private fun takePhoto() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CODE)
    }

    private fun selectImageFromGallery() {

        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(
                intent,
                "Please select..."
            ),
            GALLERY_REQUEST_CODE
        )
    }



    private fun uploadImageToFirebase(fileUri: Uri) {
        if (fileUri != null) {
            val fileName = UUID.randomUUID().toString() +".jpg"


            val refStorage = storage.reference.child("feed/$fileName")

            refStorage.putFile(fileUri)
                .addOnSuccessListener(
                    OnSuccessListener<UploadTask.TaskSnapshot> { taskSnapshot ->
                        taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                            imageUrl = it.toString()
                        }
                    })

                ?.addOnFailureListener(OnFailureListener { e ->
                    print(e.message)
                })
            Toast.makeText(activity, "foto subida", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveImageToInternalStorage(drawableId:Int):Uri{
        // Get the image from drawable resource as drawable object
        val drawable = ContextCompat.getDrawable(requireContext().applicationContext,drawableId)

        // Get the bitmap from drawable object
        val bitmap = (drawable as BitmapDrawable).bitmap

        // Get the context wrapper instance
        val wrapper = ContextWrapper(requireContext().applicationContext)

        // Initializing a new file
        // The bellow line return a directory in internal storage
        var file = wrapper.getDir("images", Context.MODE_PRIVATE)


        // Create a file to save the image
        file = File(file, "${UUID.randomUUID()}.jpg")

        try {
            // Get the file output stream
            val stream: OutputStream = FileOutputStream(file)

            // Compress bitmap
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)

            // Flush the stream
            stream.flush()

            // Close stream
            stream.close()
        } catch (e: IOException){ // Catch the exception
            e.printStackTrace()
        }

        // Return the saved image uri
        return Uri.parse(file.absolutePath)
    }

    fun bitmapToFile(bitmap: Bitmap, fileNameToSave: String): File? { // File name like "image.png"
        //create a file to write bitmap data
        var file: File? = null
        return try {
            file = File(Environment.getExternalStorageDirectory().toString() + File.separator + fileNameToSave)
            file.createNewFile()

            //Convert bitmap to byte array
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
            val bitmapdata = bos.toByteArray()

            //write the bytes in file
            val fos = FileOutputStream(file)
            fos.write(bitmapdata)
            fos.flush()
            fos.close()
            file
        } catch (e: Exception) {
            e.printStackTrace()
            file // it will return null
        }
    }
}
