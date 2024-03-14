package com.example.viewexamples

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.viewexamples.databinding.RecyclerViewItemBinding

class RecyclerViewAdapter(
    private val elements: List<DataShapeElement>,
    val showDialog: (shapeName: String) -> Unit
) : RecyclerView.Adapter<RecyclerViewAdapter.ElementViewHolder>() {

    override fun getItemCount() = elements.size

    inner class ElementViewHolder(private val binding: RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(element: DataShapeElement) {
            with(binding) {
                tvElementName.text = element.shapeName
                ivImage.setImageResource(element.imageResId)
                root.setOnClickListener { showDialog(element.shapeName) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ElementViewHolder(
            RecyclerViewItemBinding
                .inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
        )

    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) =
        holder.bind(elements[position])
}