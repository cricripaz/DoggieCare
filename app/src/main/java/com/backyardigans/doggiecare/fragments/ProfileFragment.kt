package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.backyardigans.doggiecare.R
import android.widget.ImageButton
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.backyardigans.doggiecare.adapters.FeedAdapter
import com.backyardigans.doggiecare.data.TemptDataSource
import com.bumptech.glide.Glide

class ProfileFragment :  Fragment() {
    private val feedAdapter = FeedAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(
            R.layout.activity_profile_fragment,
            container, false
        )
        val button = view.findViewById<View>(R.id.profilebotonconfig) as ImageButton
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val someFragment: Fragment = ConfigPopUpFragment()
                val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
                transaction.replace(
                    R.id.container_configurations_popup,
                    someFragment
                )
                transaction.addToBackStack(null)
                transaction.commit()
            }
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.misposts)
        recyclerView.adapter = feedAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        feedAdapter.addAll(TemptDataSource.postList)

    }
}