package com.backyardigans.doggiecare.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.backyardigans.doggiecare.Model.Feed
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.databinding.ActivityCardsBinding

class FeedAdapter(): RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

   val elementList:MutableList<Feed> = mutableListOf()
    private var onFeedItemClickListener: ((feed: Feed) -> Unit)? = null

    fun addAll(newElementList:MutableList<Feed>){
        elementList.clear()
        elementList.addAll(newElementList)
        notifyDataSetChanged()
    }

    fun setOnFeedItemClickListener(onFeedItemClickListener: ((feed: Feed) -> Unit)?) {
        this.onFeedItemClickListener = onFeedItemClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)

        return ViewHolder(layoutInflater.inflate(R.layout.activity_cards, parent, false))
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ActivityCardsBinding.bind(view)



        fun bind(feed: Feed) {
            binding.imgFeed.setImageResource(R.drawable.ic_img_dog)//todo to url
            binding.tvFeedUserName.text=feed.userNick
            binding.tvAnimalAge.text=feed.AnimalAge
            binding.tvAnimalName.text=feed.AnimalName
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(elementList[position])
        holder.itemView.setOnClickListener {
            onFeedItemClickListener?.invoke(elementList[position])
        }
    }

    override fun getItemCount(): Int {
        return elementList.size
    }
}
