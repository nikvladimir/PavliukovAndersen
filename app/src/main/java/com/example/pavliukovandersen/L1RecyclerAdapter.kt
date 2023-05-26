package com.example.pavliukovandersen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class L1RecyclerAdapter(
    private val elements: ArrayList<ShapeElement>,
    val showDialog: (shapeName: String) -> Unit
) : RecyclerView.Adapter<L1RecyclerAdapter.ElementViewHolder>() {

    override fun getItemCount() = elements.size

    inner class ElementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivImage: ShapeableImageView = itemView.findViewById(R.id.ivImage)
        val tvHeading: TextView = itemView.findViewById(R.id.tvHeading)
        val rvItem: LinearLayout = itemView.findViewById(R.id.recycler_view_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ElementViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.recycler_view_item, parent, false)
        )

    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) {
        var currentItem = elements[position]
        holder.ivImage.setImageResource(currentItem.titleImage)
        holder.tvHeading.text = currentItem.heading
        holder.rvItem.setOnClickListener {
            showDialog("${position + 1} is: ${currentItem.heading}")
        }
    }
}