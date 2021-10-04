package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.backyardigans.doggiecare.R

class EditProfileFragment : Fragment() {

    override fun  onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?): View {

        val view: View = inflater.inflate(
            R.layout.fragment_edit_profile,
            container, false
        )
        val button = view.findViewById<View>(R.id.button_save) as Button
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

            }
        })
        return view
    }
}