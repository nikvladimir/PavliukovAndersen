package com.example.pavliukovandersen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class L1RecyclerAdapter(
    private val elements: List<ShapeElement>,
    val showDialog: (shapeName: String) -> Unit
) : RecyclerView.Adapter<L1RecyclerAdapter.ElementViewHolder>() {

    override fun getItemCount() = elements.size

    inner class ElementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(element: ShapeElement) {
            itemView.findViewById<TextView>(R.id.tvElementName).text = element.shapeName
            itemView.findViewById<ImageView>(R.id.ivImage).setImageResource(element.imageResId)
            itemView.setOnClickListener { showDialog(element.shapeName) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ElementViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.recycler_view_item, parent, false)
        )

    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) {
        holder.bind(elements[position])
    }
}