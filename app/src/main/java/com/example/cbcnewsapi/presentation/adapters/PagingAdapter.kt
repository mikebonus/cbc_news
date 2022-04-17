package com.example.cbcnewsapi.presentation.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cbcnewsapi.databinding.NewsItemBinding
import com.example.cbcnewsapi.domain.model.News
import com.example.cbcnewsapi.presentation.ui.DetailActivity

class PagingAdapter : PagingDataAdapter<News, PagingAdapter.NewsViewHolder>(NEWS_COMPARATOR) {

    val TAG = "TAG"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        // adapter will do the adaptation to RV (3rd-param)..
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }

        holder.itemView.setOnClickListener {
//            Log.d(TAG, "onBindViewHolder: clicked this item.........at position $position ")
//            Logld(TAG, "onBindViewHolder: clicked this item-title...at position ${currentItem?.title} ")
//            Log.d(TAG, "onBindViewHolder: clicked this item-date....at position ${currentItem?.type} ")
            Log.d(TAG, "onBindViewHolder: clicked this item-image...at position ${currentItem?.images?.square_140}")
            val intentTwo = Intent(holder.itemView.context, DetailActivity::class.java)
            intentTwo.putExtra("image1234", currentItem?.images?.square_140)
            intentTwo.putExtra("title", currentItem?.title)
            intentTwo.putExtra("type", currentItem?.type)
            intentTwo.putExtra("publish_date", currentItem?.readablePublishedAt)
            Log.d(TAG, "onBindViewHolder: clicked this item-image223344...at position ${currentItem?.images?.square_140}")

            ContextCompat.startActivity(holder.itemView.context, intentTwo, null)

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

    companion object {
        private val NEWS_COMPARATOR = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News) =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: News, newItem: News) =
                oldItem == newItem
        }
    }

}