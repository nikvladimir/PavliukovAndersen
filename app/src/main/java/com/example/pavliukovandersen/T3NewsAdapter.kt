package com.example.pavliukovandersen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pavliukovandersen.databinding.T3RecyclerViewNewsItemBinding
import com.example.pavliukovandersen.retrofit.ArticleDto

class T3NewsAdapter(private val itemClick: (ArticleDto) -> Unit) :
    ListAdapter<ArticleDto, T3NewsAdapter.ViewHolder>(Comparator()) {

    class ViewHolder(itemView: View, private val itemClick: (ArticleDto) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private val binding = T3RecyclerViewNewsItemBinding.bind(itemView)

        fun bind(articleDto: ArticleDto) = with(binding) {
            newsTitle.text = articleDto.title
            newsAuthor.text = articleDto.author
            newsPublishedAt.text = articleDto.publishedAt
            itemView.setOnClickListener { itemClick(articleDto) }
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
            .inflate(R.layout.t3_recycler_view_news_item, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}