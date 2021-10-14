package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.backyardigans.doggiecare.R

class ConfigPopUpFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val  editar= view?.findViewById<View>(R.id.popupEditarPerfil) as TextView
        editar?.setOnClickListener {
            //hacer con navigation
            Toast.makeText(activity, "editar perfil", Toast.LENGTH_LONG).show()
        }

        val  verificar= view?.findViewById<View>(R.id.popupSolicitarVerificacion) as TextView
        editar?.setOnClickListener {
            Toast.makeText(activity, "Su solicitud será procesada por nuestro equipo", Toast.LENGTH_LONG).show()
        }

        val  modo= view?.findViewById<View>(R.id.popupCambiarModo) as TextView
        editar?.setOnClickListener {
            Toast.makeText(activity, "Cambiado a modo nocturno", Toast.LENGTH_LONG).show()
        }

        val  salir= view?.findViewById<View>(R.id.popupSalir) as TextView
        editar?.setOnClickListener {
            Toast.makeText(activity, "Saliendo de la aplicación", Toast.LENGTH_LONG).show()
        }


        return inflater.inflate(R.layout.activity_search_fragment, container, false)
    }
}