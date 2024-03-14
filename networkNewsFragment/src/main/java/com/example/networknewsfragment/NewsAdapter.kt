package com.example.networknewsfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.networknewsfragment.databinding.RecyclerViewNewsItemBinding
import com.example.networknewsfragment.retrofit.ArticleDto

class NewsAdapter(
    private val itemClick: (ArticleDto, View, String) -> Unit
) : ListAdapter<ArticleDto, NewsAdapter.ViewHolder>(Comparator()) {

    class ViewHolder(
        itemView: View,
        private val itemClick: (ArticleDto, View, String) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val binding = RecyclerViewNewsItemBinding.bind(itemView)

        fun bind(articleDto: ArticleDto, position: Int) = with(binding) {

            newsAuthor.text = articleDto.author
            newsPublishedAt.text = articleDto.publishedAt
            newsTitle.text = articleDto.title
            newsTitle.transitionName = "shared_element_transition_$position"

            itemView.setOnClickListener {
                itemClick(articleDto, newsTitle, "shared_element_transition_$position")
            }
        }
    }

    class Comparator : DiffUtil.ItemCallback<ArticleDto>() {
        override fun areItemsTheSame(oldItem: ArticleDto, newItem: ArticleDto): Boolean {
            return oldItem.author == newItem.author && oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ArticleDto, newItem: ArticleDto): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_news_item, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }
}