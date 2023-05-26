package com.example.pavliukovandersen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class L1RecyclerViewFragment : Fragment() {
    private lateinit var adapter: L1RecyclerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var shapesArrayList: ArrayList<ShapeData>

    lateinit var imageId: Array<Int>
    lateinit var heading: Array<String>

    private fun dataInitialize(){

        shapesArrayList = arrayListOf<ShapeData>()

        imageId = arrayOf(
            R.drawable.arrow,
            R.drawable.circle,
            R.drawable.hexagon,
            R.drawable.kite,
            R.drawable.oval,
            R.drawable.octagon,
            R.drawable.rectangle,
            R.drawable.shape,
            R.drawable.square,
            R.drawable.triangle,
        )

        heading = arrayOf(
            getString(R.string.head_arrow),
            getString(R.string.head_circle),
            getString(R.string.head_hexagon),
            getString(R.string.head_kite),
            getString(R.string.head_oval),
            getString(R.string.head_octagon),
            getString(R.string.head_rectangle),
            getString(R.string.head_shape),
            getString(R.string.head_square),
            getString(R.string.head_triangle)
        )

        for (i in imageId.indices){
            val shapeData = ShapeData(imageId[i], heading[i])
            shapesArrayList.add(shapeData)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.l1_fragment_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialize()
        val layoutManager = LinearLayoutManager(context)
        val toast = Toast.makeText(context, "TOAAAAAST",  Toast.LENGTH_SHORT)
        recyclerView = view.findViewById(R.id.recyclerViewFragment)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = L1RecyclerAdapter(shapesArrayList, toast)
        recyclerView.adapter = adapter
    }
}