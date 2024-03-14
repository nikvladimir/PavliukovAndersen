package com.example.servicemusicplayer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.servicemusicplayer.databinding.SongItemBinding

class PlayListAdapter(private var playList: ArrayList<DataPlayList>) :
    RecyclerView.Adapter<PlayListAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: SongItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataPlayList) {
            with(binding) {
                artistTv.text = item.artist
                songNameTv.text = item.trackName
                genreTv.text = item.genre
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(
            SongItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun getItemCount(): Int = playList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) =
        holder.bind(playList[position])
}