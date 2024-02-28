package com.example.pavliukovexamples

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pavliukovexamples.databinding.T2SongItemBinding

class T2PlayListAdapter(private var playList: ArrayList<T2DataPlayList>) :
    RecyclerView.Adapter<T2PlayListAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: T2SongItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T2DataPlayList) {
            with(binding) {
                artistTv.text = item.artist
                songNameTv.text = item.trackName
                genreTv.text = item.genre
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(
            T2SongItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun getItemCount(): Int = playList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) =
        holder.bind(playList[position])
}