package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.backyardigans.doggiecare.R

class EditProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }
}