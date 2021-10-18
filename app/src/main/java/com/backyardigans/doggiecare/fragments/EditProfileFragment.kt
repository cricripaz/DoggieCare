package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.backyardigans.doggiecare.R

class EditProfileFragment : Fragment() {

    private lateinit var btGuardar: View
    override fun  onCreateView(inflater: LayoutInflater,
                               container: ViewGroup?,
                               savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btGuardar = view.findViewById(R.id.button_save)

        btGuardar.setOnClickListener {
            val goToProfile = WelcomeBioFragmentDirections.actionWelcomeBioFragmentToFeedActivity()
            findNavController().navigate(goToProfile)
        }
    }
}