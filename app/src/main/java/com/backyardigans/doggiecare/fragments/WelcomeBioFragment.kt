package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.base.StepsBaseFragment
import com.backyardigans.doggiecare.databinding.FragmentWelcomeBioBinding

class WelcomeBioFragment : StepsBaseFragment() {

    private lateinit var btlisto: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_welcome_bio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       btlisto = view.findViewById(R.id.welcomelisto)

       btlisto.setOnClickListener {
    val goToFeed = WelcomeBioFragmentDirections.actionWelcomeBioFragmentToFeedActivity()
           findNavController().navigate(goToFeed)
        }
    }
}