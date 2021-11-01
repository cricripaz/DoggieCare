package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.backyardigans.doggiecare.Preferences.UserApplication.Companion.prefs
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.databinding.FragmentEditProfileBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class EditProfileFragment : BottomSheetDialogFragment() {
    private val db = FirebaseFirestore.getInstance()


    private var _binding: FragmentEditProfileBinding?=null
    private val binding get() = _binding!!


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


        binding.editImageButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Toast.makeText(context, "Woops! todavía no puedes cambiar tu foto de perfil", Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }

    //TODO no permitiir si no tiene internet

}