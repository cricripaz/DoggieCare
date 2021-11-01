package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.backyardigans.doggiecare.Model.Feed
import com.backyardigans.doggiecare.adapters.FeedAdapter
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.databinding.ActivityHomeFragmentBinding
import com.backyardigans.doggiecare.viewModel.FeedViewModel

class HomeFragment : Fragment() {

    private var _binding: ActivityHomeFragmentBinding?=null
    private val binding get() = _binding!!
    private val feedAdapter = FeedAdapter()
    private val feedModel : FeedViewModel by activityViewModels()

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

        feedModel.feedList.observe(viewLifecycleOwner , {
            feedAdapter.addAll(it as MutableList<Feed>)
        }
        )
        feedModel.updatePost()

        feedAdapter.setOnFeedItemClickListener {

            val userNick = it.userNick
            val animalName:String=it.AnimalName
            val animalAge:String=it.AnimalAge
            val animalBreed:String=it.AnimalBreed
            val description:String=it.description
            val urlImage = it.img//todo agregar img

            val directions = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                userNick, animalName, animalAge,animalBreed,description)
            findNavController().navigate(directions)
        }
    }


}