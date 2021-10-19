package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.backyardigans.doggiecare.Model.Chat
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.adapters.ChatAdapter
import com.backyardigans.doggiecare.adapters.FeedAdapter
import com.backyardigans.doggiecare.data.ChatDataSource
import com.backyardigans.doggiecare.data.TemptDataSource

class ChatFragment : Fragment() {

   private val chatAdapter  = ChatAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.activity_chat_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvChat)
        recyclerView.adapter = chatAdapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        chatAdapter.addAll(ChatDataSource.chatList)
    }

}