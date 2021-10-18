package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.backyardigans.doggiecare.R

class ContactPopUpFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val  contactar= view?.findViewById<View>(R.id.popupContactar) as TextView
        contactar?.setOnClickListener {
            //integrar
            Toast.makeText(activity, "abriendo mensajería", Toast.LENGTH_LONG).show()
        }
        val  verperfil= view?.findViewById<View>(R.id.popupVerPerfil) as TextView
        verperfil?.setOnClickListener {
            //Ver perfil de usuario
            Toast.makeText(activity, "Redigiriendo", Toast.LENGTH_LONG).show()
        }
        val  denunciar= view?.findViewById<View>(R.id.popupDenunciar) as TextView
        denunciar?.setOnClickListener {
            //implementar?
            Toast.makeText(activity, "Denuncia enviada", Toast.LENGTH_LONG).show()
        }
        val  bloquear= view?.findViewById<View>(R.id.popupBloquear) as TextView
        bloquear?.setOnClickListener {
            //usar dialogo?
            Toast.makeText(activity, "¿Seguro que desea bloquear a este usuario?", Toast.LENGTH_LONG).show()
        }




        return inflater.inflate(R.layout.fragment_popup_contact_menu, container, false)
    }

}