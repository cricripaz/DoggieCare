package com.backyardigans.doggiecare

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.backyardigans.doggiecare.Model.Feed
import com.backyardigans.doggiecare.R.drawable.ic_icon_perrito
import com.backyardigans.doggiecare.databinding.ActivityCardsBinding

class FeedAdapter(): RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

   val elementList:MutableList<Feed> = mutableListOf()

    fun addAll(newElementList:MutableList<Feed>){
        elementList.clear()
        elementList.addAll(newElementList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)

        return ViewHolder(layoutInflater.inflate(R.layout.activity_cards, parent, false))
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ActivityCardsBinding.bind(view)
        //Todo ??


        fun bind(feed: Feed) {
            binding.imgFeed.setImageResource(feed.img)
            binding.tvFeedUserName.text=feed.userName
            binding.tvFeedNickName.text=feed.nickName
            binding.tvAnimalAge.text=feed.AnimalAge
            binding.tvAnimalName.text=feed.AnimalName
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(elementList[position])
    }

    override fun getItemCount(): Int {
        return elementList.size
    }
}
