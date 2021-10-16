package com.backyardigans.doggiecare.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.backyardigans.doggiecare.R
import android.widget.ImageButton
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.backyardigans.doggiecare.adapters.FeedAdapter
import com.backyardigans.doggiecare.data.TemptDataSource
import com.backyardigans.doggiecare.databinding.ActivityAddFragmentBinding
import com.backyardigans.doggiecare.databinding.ActivityProfileFragmentBinding
import com.backyardigans.doggiecare.viewModel.ProfileViewModel
import com.bumptech.glide.Glide

class ProfileFragment :  Fragment() {
    private var _binding: ActivityProfileFragmentBinding?=null
    private val binding get() = _binding!!

    private val profileViewModel:ProfileViewModel by viewModels()
    private val feedAdapter = FeedAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ActivityProfileFragmentBinding.inflate(inflater, container, false)






        profileViewModel.profileModel.observe(viewLifecycleOwner, Observer {
            binding.bioUsuario.text = it.bio
            binding.idUsuario.text = it.nickName

        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profilebotonconfig.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                findNavController().navigate(R.id.action_profileFragment_to_configPopUpFragment2)

                //TODO poner de nuevo el drawable round para el configPopUp pero con fondo transparente no me sale
            }
        })


        val recyclerView = view.findViewById<RecyclerView>(R.id.misposts)
        recyclerView.adapter = feedAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        feedAdapter.addAll(TemptDataSource.postList)

    }
}