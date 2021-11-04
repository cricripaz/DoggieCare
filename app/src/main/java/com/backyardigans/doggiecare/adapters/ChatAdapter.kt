package com.backyardigans.doggiecare.adapters

import android.content.Context
import android.content.Intent
import android.graphics.ColorSpace
import android.text.Layout
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.backyardigans.doggiecare.Model.Chat
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.activities.MessageActivity
import com.backyardigans.doggiecare.databinding.ChatModelBinding

class ChatAdapter(): RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    val elementList:MutableList<Chat> = mutableListOf()

    fun addAll(newElementList : MutableList<Chat>){
        elementList.clear()
        elementList.addAll(newElementList)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        return ViewHolder(layoutInflater.inflate(R.layout.chat_model,parent,false))

    }



    class ViewHolder(view:View): RecyclerView.ViewHolder(view){

        val binding = ChatModelBinding.bind(view)

        fun bind(chat : Chat) {
            binding.nameChat.text = chat.title
            binding.messageChat.text = chat.description
           binding.imageProfile.setImageResource(chat.img)
        }

    }

    override fun onBindViewHolder(holder: ChatAdapter.ViewHolder, position: Int) {

            holder.bind(elementList[position])
            holder.itemView.setOnClickListener{ v ->
                val intent = Intent(v.context, MessageActivity::class.java)
                v.context.startActivity(intent)
            }
        }

    override fun getItemCount(): Int {

        return elementList.size
    }


}





