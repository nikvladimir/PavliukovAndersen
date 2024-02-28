package com.example.pavliukovexamples

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pavliukovexamples.databinding.T1FragmentRecyclerViewBinding


class T1RecyclerViewFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var shapesArrayList: List<T1DataShapeElement>
    private lateinit var binding: T1FragmentRecyclerViewBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shapesArrayList = getListOfShapeElements()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = T1FragmentRecyclerViewBinding.inflate(layoutInflater)

        binding.tvRecyclerViewNotification.text = getString(R.string.recycler_view_notification)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.t3RecyclerViewNewsFragment
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = T1RecyclerViewAdapter(shapesArrayList, ::showDialog)
        }
    }

    private fun showDialog(shapeName: String) = T1DialogFragment
        .newInstance(shapeName)
        .show(requireActivity().supportFragmentManager, "dialog")

    private fun getListOfShapeElements() =
        listOf(
            T1DataShapeElement(getString(R.string.arrow), R.drawable.arrow),
            T1DataShapeElement(getString(R.string.circle), R.drawable.circle),
            T1DataShapeElement(getString(R.string.hexagon), R.drawable.hexagon),
            T1DataShapeElement(getString(R.string.kite), R.drawable.kite),
            T1DataShapeElement(getString(R.string.oval), R.drawable.oval),
            T1DataShapeElement(getString(R.string.octagon), R.drawable.octagon),
            T1DataShapeElement(getString(R.string.rectangle), R.drawable.rectangle),
            T1DataShapeElement(getString(R.string.shape), R.drawable.shape),
            T1DataShapeElement(getString(R.string.square), R.drawable.square),
            T1DataShapeElement(getString(R.string.triangle), R.drawable.triangle)
        )
}