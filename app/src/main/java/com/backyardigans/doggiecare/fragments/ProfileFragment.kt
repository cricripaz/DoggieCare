package com.backyardigans.doggiecare.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.backyardigans.doggiecare.R
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide

class ProfileFragment :  Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val view: View = inflater.inflate(
            R.layout.activity_profile_fragment,
            container, false
        )
        val button = view.findViewById<View>(R.id.button_editar) as Button
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val someFragment: Fragment = EditProfileFragment()
                val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
                transaction.replace(
                    R.id.container_edit_profile,
                    someFragment
                )
                transaction.addToBackStack(null)
                transaction.commit()
            }
        })
        return view
    }
}