package com.example.pavliukovandersen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class L1RecyclerViewFragment : Fragment() {
    private lateinit var adapter: L1RecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var shapesArrayList: List<ShapeElement>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getListOfShapeElements()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.l1_fragment_recycler_view, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recyclerViewFragment)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = L1RecyclerViewAdapter(shapesArrayList, ::showDialog)
        recyclerView.adapter = adapter
    }

    private fun showDialog(shapeName: String) = L1DialogFragment
        .newInstance(shapeName)
        .show(requireActivity().supportFragmentManager, "dialog")

    private fun getListOfShapeElements() =
        listOf(
            ShapeElement(getString(R.string.arrow), R.drawable.arrow),
            ShapeElement(getString(R.string.circle), R.drawable.circle),
            ShapeElement(getString(R.string.hexagon), R.drawable.hexagon),
            ShapeElement(getString(R.string.kite), R.drawable.kite),
            ShapeElement(getString(R.string.oval), R.drawable.oval),
            ShapeElement(getString(R.string.octagon), R.drawable.octagon),
            ShapeElement(getString(R.string.rectangle), R.drawable.rectangle),
            ShapeElement(getString(R.string.shape), R.drawable.shape),
            ShapeElement(getString(R.string.square), R.drawable.square),
            ShapeElement(getString(R.string.triangle), R.drawable.triangle)
        )
}