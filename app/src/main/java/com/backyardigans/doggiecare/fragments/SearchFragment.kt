package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.backyardigans.doggiecare.Model.Feed
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.adapters.FeedAdapter
import com.backyardigans.doggiecare.data.TemptDataSource
import com.backyardigans.doggiecare.databinding.ActivitySearchFragmentBinding
import com.backyardigans.doggiecare.viewModel.FeedViewModel
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class SearchFragment : Fragment() {
    private var _binding: ActivitySearchFragmentBinding? = null
    private val binding get() = _binding!!
    private val feedAdapter = FeedAdapter()
    private val feedModel: FeedViewModel by activityViewModels()


    val db = Firebase.firestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ActivitySearchFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvFeedSearchFragment)
        recyclerView.adapter = feedAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        LinearSnapHelper().attachToRecyclerView(recyclerView)

        binding.textsearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                    feedModel.feedListSearch.observe(viewLifecycleOwner, {
                        feedAdapter.addAll(it as MutableList<Feed>)


                    })
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    feedModel.updatePostSearch(newText)
                }

                return false
            }


        })


        feedAdapter.setOnFeedItemClickListener {
            val userNick = it.userNick
            val animalName: String = it.AnimalName
            val animalAge: String = it.AnimalAge
            val animalBreed: String = it.AnimalBreed
            val description: String = it.description
            val urlImage = it.urlImage

            val directions = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                userNick, animalName, animalAge, animalBreed, description
            )
            findNavController().navigate(directions)
        }
    }


     suspend fun getAllPosts(search:String): List<Feed> {
        val response = db.collection("publicaciones").whereIn(
            search, listOf("userNick", "animalName")).get().await()
        return response.toObjects(Feed::class.java)
    }

}