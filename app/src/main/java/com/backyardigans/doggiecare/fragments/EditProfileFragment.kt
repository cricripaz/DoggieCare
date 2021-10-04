package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.backyardigans.doggiecare.R

class EditProfileFragment : Fragment() {

    override fun  onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?): View {

        val view: View = inflater.inflate(R.layout.fragment_edit_profile, container, false)

        val button = view.findViewById<View>(R.id.button_save) as Button
        button.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {
                val ft = activity!!.getSupportFragmentManager().beginTransaction().remove(this@EditProfileFragment).commit()
            }
        })

        val editphoto = view.findViewById<View>(R.id.edit_imageButton) as ImageButton
        editphoto.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Toast.makeText(context, "Woops! todavía no puedes cambiar tu foto de perfil", Toast.LENGTH_SHORT).show()
            }
        })

        return view
    }


}