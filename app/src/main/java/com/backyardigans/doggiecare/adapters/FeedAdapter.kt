package com.backyardigans.doggiecare.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.backyardigans.doggiecare.Model.Feed
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.databinding.ActivityCardsBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

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

    class ViewHolder( val view: View): RecyclerView.ViewHolder(view){
        val binding = ActivityCardsBinding.bind(view)



        fun bind(feed: Feed) {
            Glide.with(view.context).load(feed.urlImage)
               .transform( CenterCrop(), GranularRoundedCorners(40F, 40F, 0F,0F))
                .placeholder(R.drawable.ic_icon_perrito) //si se puede ponerle tinta al svg
                .into(binding.imgFeed)
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
