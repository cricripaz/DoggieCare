package com.backyardigans.doggiecare.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.backyardigans.doggiecare.R
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.backyardigans.doggiecare.Model.Feed
import com.backyardigans.doggiecare.Model.Profile
import com.backyardigans.doggiecare.Preferences.UserApplication
import com.backyardigans.doggiecare.Preferences.UserApplication.Companion.prefs
import com.backyardigans.doggiecare.adapters.FeedAdapter
import com.backyardigans.doggiecare.data.TemptDataSource
import com.backyardigans.doggiecare.databinding.ActivityProfileFragmentBinding
import com.backyardigans.doggiecare.viewModel.FeedViewModel
import com.backyardigans.doggiecare.viewModel.ProfileViewModel
import com.google.firebase.firestore.FirebaseFirestore

class ProfileFragment :  Fragment() {
    private var _binding: ActivityProfileFragmentBinding? = null
    private val binding get() = _binding!!
    private val profileViewModel: ProfileViewModel by activityViewModels()
    private val feedAdapter = FeedAdapter()
    private val feedModelProfile : FeedViewModel by activityViewModels()


    private val db = FirebaseFirestore.getInstance()


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ActivityProfileFragmentBinding.inflate(inflater, container, false)

        atualizarSinPrefs()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.profilebotonconfig.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                findNavController().navigate(R.id.action_profileFragment_to_configPopUpFragment2)
            }
        })


        val recyclerView = view.findViewById<RecyclerView>(R.id.misposts)
        recyclerView.adapter = feedAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        LinearSnapHelper().attachToRecyclerView(recyclerView)

        feedModelProfile.feedList.observe(viewLifecycleOwner , {
            feedAdapter.addAll(it as MutableList<Feed>)
        }
        )
        feedModelProfile.updatePost()

        feedAdapter.setOnFeedItemClickListener {
            val directions = ProfileFragmentDirections.actionProfileFragmentToOptionsPopUpFragment()
            findNavController().navigate(directions)
        }
        binding.nombreUsuario.text = prefs.getEmail()

        profileViewModel.profileModel.observe(viewLifecycleOwner, Observer {
            binding.bioUsuario.text = it.bio
            binding.idUsuario.text = it.nickName

        })

    }


    private fun atualizarSinPrefs() {
        if (prefs.getEmail().isNotEmpty()) {//todo Agregar if is not online

            db.collection("users").document(UserApplication.prefs.getEmail()).get()
                .addOnSuccessListener {
                    if (it.exists()) {
                        val defaultBio = it.data?.get("userBio") as String
                        profileViewModel.actualizar(Profile(null, defaultBio))


                    }
                }
            db.collection("users").document(UserApplication.prefs.getEmail()).get()
                .addOnSuccessListener {
                    if (it.exists()) {
                        val defaultNick = it.data?.get("userNick") as String
                        profileViewModel.actualizar(Profile(defaultNick, null))


                    }
                }
        }
    }

}
