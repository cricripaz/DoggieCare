package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.backyardigans.doggiecare.R
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.backyardigans.doggiecare.Model.Feed
import com.backyardigans.doggiecare.Preferences.UserApplication.Companion.prefs
import com.backyardigans.doggiecare.adapters.FeedAdapter
import com.backyardigans.doggiecare.databinding.ActivityProfileFragmentBinding
import com.backyardigans.doggiecare.viewModel.FeedViewModel
import com.backyardigans.doggiecare.viewModel.ProfileViewModel
import com.google.firebase.firestore.FirebaseFirestore

class ProfileFragment : Fragment() {
    private var _binding: ActivityProfileFragmentBinding? = null
    private val binding get() = _binding!!
    private val profileViewModel: ProfileViewModel by activityViewModels()
    private val feedAdapter = FeedAdapter()
    private val feedModel: FeedViewModel by activityViewModels()


    private val db = FirebaseFirestore.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ActivityProfileFragmentBinding.inflate(inflater, container, false)
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

        feedModel.feedListProfile.observe(viewLifecycleOwner, {
            feedAdapter.addAll(it as MutableList<Feed>)
        })
        feedModel.updatePostProfile()

        feedAdapter.setOnFeedItemClickListener {
            val bundle = bundleOf("previous" to "post")
            findNavController().navigate(R.id.action_profileFragment_to_optionsPopUpFragment, bundle)

        }
        binding.nombreUsuario.text = prefs.getEmail()

        profileViewModel.userProfile.observe(viewLifecycleOwner, Observer {
            binding.bioUsuario.text = it.userBio
            binding.idUsuario.text = it.userNick
            prefs.saveUser(it.userNick)


        })

        profileViewModel.updateProfile()

    }


}
