package com.example.pavliukovandersen

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class L1RecyclerAdapter(
    private val newsList: ArrayList<ShapeData>,
    val showDialog : () -> Unit
    ) :
    RecyclerView.Adapter<L1RecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleImage: ShapeableImageView = itemView.findViewById(R.id.ivImage)
        val tvHeading: TextView = itemView.findViewById(R.id.tvHeading)
        val item: LinearLayout = itemView.findViewById(R.id.recycler_view_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var currentItem = newsList[position]
        holder.titleImage.setImageResource(currentItem.titleImage)
        holder.tvHeading.text = currentItem.heading
        holder.item.setOnClickListener {
            showDialog()
            Log.d(this.toString(), "LOG toast")
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }


}