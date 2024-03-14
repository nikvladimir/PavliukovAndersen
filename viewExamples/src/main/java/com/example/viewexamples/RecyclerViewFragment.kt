package com.example.viewexamples

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.viewexamples.databinding.FragmentRecyclerViewBinding


class RecyclerViewFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var shapesArrayList: List<DataShapeElement>
    private lateinit var binding: FragmentRecyclerViewBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shapesArrayList = getListOfShapeElements()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerViewBinding.inflate(layoutInflater)

        binding.tvRecyclerViewNotification.text = getString(R.string.recycler_view_notification)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.t3RecyclerViewNewsFragment
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = RecyclerViewAdapter(shapesArrayList, ::showDialog)
        }
    }

    private fun showDialog(shapeName: String) = DialogFragment.newInstance(shapeName)
        .show(requireActivity().supportFragmentManager, "dialog")

    private fun getListOfShapeElements() =
        listOf(
            DataShapeElement(getString(R.string.arrow), R.drawable.arrow),
            DataShapeElement(getString(R.string.circle), R.drawable.circle),
            DataShapeElement(getString(R.string.hexagon), R.drawable.hexagon),
            DataShapeElement(getString(R.string.kite), R.drawable.kite),
            DataShapeElement(getString(R.string.oval), R.drawable.oval),
            DataShapeElement(getString(R.string.octagon), R.drawable.octagon),
            DataShapeElement(getString(R.string.rectangle), R.drawable.rectangle),
            DataShapeElement(getString(R.string.shape), R.drawable.shape),
            DataShapeElement(getString(R.string.square), R.drawable.square),
            DataShapeElement(getString(R.string.triangle), R.drawable.triangle)
        )
}