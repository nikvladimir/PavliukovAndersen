package com.example.pavliukovandersen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pavliukovandersen.databinding.T3RecyclerViewNewsItemBinding
import com.example.pavliukovandersen.retrofit.ArticleDto

class T3NewsAdapter : ListAdapter<ArticleDto, T3NewsAdapter.Holder>(Comparator()) {

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = T3RecyclerViewNewsItemBinding.bind(view)

        fun bind(articleDto: ArticleDto) = with(binding) {
            tvTitle.text = articleDto.title
            tvSouse.text = articleDto.source.name
            tvDescription.text = articleDto.description
        }
    }

    class Comparator : DiffUtil.ItemCallback<ArticleDto>() {
        override fun areItemsTheSame(oldItem: ArticleDto, newItem: ArticleDto): Boolean {
            return oldItem.author == newItem.author
        }

        override fun areContentsTheSame(oldItem: ArticleDto, newItem: ArticleDto): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.t3_recycler_view_news_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}