package com.example.cbcnewsapi.presentation.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.codinginflow.simplecachingexample.util.Resource
import com.example.cbcnewsapi.presentation.adapters.NewsAdapter
import com.example.cbcnewsapi.presentation.adapters.PagingAdapter
import com.example.cbcnewsapi.databinding.ActivityNewsBinding
import com.example.cbcnewsapi.utils.Extender
import com.example.cbcnewsapi.presentation.viewmodel.VideoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_news.*

@AndroidEntryPoint
class VideoActivity : AppCompatActivity() {

    private val viewModel: VideoViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsAdapter = NewsAdapter()
        val newsPageAdapter = PagingAdapter()

        if (Extender.isOnline(applicationContext)) {
            binding.apply {
                recyclerView.apply {
                    adapter = newsPageAdapter
                    recyclerView.hasFixedSize()
                    layoutManager = LinearLayoutManager(this@VideoActivity)
                    recyclerView.adapter = newsPageAdapter.withLoadStateHeaderAndFooter(
                        header = NewsLoadStateAdapter { newsPageAdapter.retry() },
                        footer = NewsLoadStateAdapter { newsPageAdapter.retry() },
                    )
                }
                viewModel.responsePage.observe(this@VideoActivity) { news ->
                    newsPageAdapter.submitData(this@VideoActivity.lifecycle, news)
                }

                viewModel.news_list_video_observable.observe(this@VideoActivity) { news ->
                    newsAdapter.submitList(news.data)
                }
            }

        } else {
            binding.apply {
                recyclerView.apply {
                    adapter = newsAdapter
                    layoutManager = LinearLayoutManager(this@VideoActivity)
                }

                viewModel.news_list_video_observable.observe(this@VideoActivity) { news ->
                    newsAdapter.submitList(news.data)
                    progressBar.isVisible =
                        news is Resource.Loading && news.data.isNullOrEmpty()
                    textViewError.isVisible =
                        news is Resource.Error && news.data.isNullOrEmpty()
                    textViewError.text = news.error?.localizedMessage
                }
            }
        }
    }

    // to prevent memory-leak
    override fun onDestroy() {
        super.onDestroy()
        recycler_View.adapter = null
    }

}