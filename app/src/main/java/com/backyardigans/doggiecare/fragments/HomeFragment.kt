package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.backyardigans.doggiecare.adapters.FeedAdapter
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.data.TemptDataSource
import com.backyardigans.doggiecare.databinding.ActivityHomeFragmentBinding
import com.backyardigans.doggiecare.databinding.FragmentEditProfileBinding

class HomeFragment : Fragment() {
    private var _binding: ActivityHomeFragmentBinding?=null
    private val binding get() = _binding!!
    private val feedAdapter = FeedAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                          savedInstanceState: Bundle?): View {
        _binding = ActivityHomeFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvFeed)
        recyclerView.adapter = feedAdapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        LinearSnapHelper().attachToRecyclerView(recyclerView)
        feedAdapter.addAll(TemptDataSource.postList)


        feedAdapter.setOnFeedItemClickListener {
            val directions = HomeFragmentDirections.actionHomeFragmentToDetailsFragment()
            findNavController().navigate(directions)
        }
    }
}