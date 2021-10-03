package com.backyardigans.doggiecare.activities

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.fragments.EditProfileFragment
import javax.xml.datatype.DatatypeFactory.newInstance


class ProfileActivity : AppCompatActivity() {
    val editFragment = EditProfileFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val button = findViewById<Button>(R.id.button_editar)
        button.setOnClickListener {
            val fragment = EditProfileFragment()
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container_edit_profile, fragment).commit()

        }
    }
}