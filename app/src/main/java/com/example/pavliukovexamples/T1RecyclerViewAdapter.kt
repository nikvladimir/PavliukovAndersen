package com.example.pavliukovexamples

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pavliukovexamples.databinding.T1RecyclerViewItemBinding

class T1RecyclerViewAdapter(
    private val elements: List<T1DataShapeElement>,
    val showDialog: (shapeName: String) -> Unit
) : RecyclerView.Adapter<T1RecyclerViewAdapter.ElementViewHolder>() {

    override fun getItemCount() = elements.size

    inner class ElementViewHolder(private val binding: T1RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(element: T1DataShapeElement) {
            with(binding) {
                tvElementName.text = element.shapeName
                ivImage.setImageResource(element.imageResId)
                root.setOnClickListener { showDialog(element.shapeName) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ElementViewHolder(
            T1RecyclerViewItemBinding
                .inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
        )

    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) =
        holder.bind(elements[position])
}