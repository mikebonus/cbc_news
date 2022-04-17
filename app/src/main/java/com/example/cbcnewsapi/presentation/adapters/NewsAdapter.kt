package com.example.cbcnewsapi.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cbcnewsapi.domain.model.News
import com.example.cbcnewsapi.databinding.NewsItemBinding

class NewsAdapter : ListAdapter<News, NewsAdapter.NewsViewHolder>(NewsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class NewsViewHolder(private val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) {
            binding.apply {

                // property #1
                Glide.with(itemView)
                    .load(news.images.square_140)
                    .into(imageViewLogo)

                // property #2, #3, #4
                textViewName.text = news.title
                textViewType.text = news.type
                textViewAddress.text = news.readablePublishedAt
            }
        }
    }

    class NewsComparator : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: News, newItem: News) =
            oldItem == newItem
    }

}

