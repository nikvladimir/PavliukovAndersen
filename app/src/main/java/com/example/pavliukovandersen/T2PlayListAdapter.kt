package com.example.pavliukovandersen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class T2PlayListAdapter(var playList: ArrayList<T2DataPlayList>) :
    RecyclerView.Adapter<T2PlayListAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvArtist: TextView = itemView.findViewById(R.id.artist_tv)
        val tvSongName: TextView = itemView.findViewById(R.id.song_name_tv)
        val tvGenre: TextView = itemView.findViewById(R.id.genre_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.t2_song_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return playList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = playList[position]
        holder.tvArtist.text = currentItem.artist
        holder.tvSongName.text = currentItem.trackName
        holder.tvGenre.text = currentItem.genre
    }
}