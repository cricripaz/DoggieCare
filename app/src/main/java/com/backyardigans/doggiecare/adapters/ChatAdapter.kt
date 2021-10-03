package com.backyardigans.doggiecare.adapters

import android.content.Context
import android.graphics.ColorSpace
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.backyardigans.doggiecare.Model.Chat
import com.backyardigans.doggiecare.R

class ChatAdapter(var mCtx : Context, var resources : Int, var items : List<Chat>):ArrayAdapter<Chat>(mCtx ,resources,items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

       val layoutInflater :LayoutInflater = LayoutInflater.from(mCtx)
       val view : View = layoutInflater.inflate(resources , null)

       val imageView : ImageView = view.findViewById(R.id.image_profile)
       val titleTextView : TextView = view.findViewById(R.id.nameChat)
       val descriptionTextView : TextView = view.findViewById(R.id.messageChat)

       var mItem : Chat = items[position]

       imageView.setImageDrawable(mCtx.resources.getDrawable(mItem.img))
       titleTextView.text = mItem.title
       descriptionTextView.text = mItem.description



        return view
    }
}