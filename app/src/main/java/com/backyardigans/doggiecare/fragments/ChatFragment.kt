package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.adapters.ChatAdapter
import com.backyardigans.doggiecare.data.ChatDataSource
import com.backyardigans.doggiecare.databinding.ActivityChatFragmentBinding

class ChatFragment : Fragment() {
    private var _binding: ActivityChatFragmentBinding? = null
    private val binding get() = _binding!!
    private val chatAdapter = ChatAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityChatFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvChat)
        recyclerView.adapter = chatAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        chatAdapter.addAll(ChatDataSource.chatList)
    }

}