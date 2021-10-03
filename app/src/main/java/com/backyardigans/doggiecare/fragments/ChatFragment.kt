package com.backyardigans.doggiecare.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.backyardigans.doggiecare.Model.Chat
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.adapters.ChatAdapter

class ChatFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.activity_chat_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var listview  = view.findViewById<ListView>(R.id.lvchat)

        var list = mutableListOf<Chat>()

        list.add(Chat("Persona 1","Hola Como Estas ?",R.drawable.ic_profile_chat))

        list.add(Chat("Persona 2","Hola Como Estas ?",R.drawable.ic_profile_chat))

        list.add(Chat("Persona 3","Hola Como Estas ?",R.drawable.ic_profile_chat))

        list.add(Chat("Persona 4","Hola Como Estas ?",R.drawable.ic_profile_chat))

        list.add(Chat("Persona 5","Hola Como Estas ?",R.drawable.ic_profile_chat))

        list.add(Chat("Persona 6","Hola Como Estas ?",R.drawable.ic_profile_chat))

        list.add(Chat("Persona 7","Hola Como Estas ?",R.drawable.ic_profile_chat))

        list.add(Chat("Persona 8","Hola Como Estas ?",R.drawable.ic_profile_chat))






        listview.adapter = ChatAdapter(view.context,R.layout.row ,list)

    }

}