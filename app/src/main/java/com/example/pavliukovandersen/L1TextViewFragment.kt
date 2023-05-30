package com.example.pavliukovandersen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class L1TextViewFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.l1_fragment_text_view, container, false)
        val tvHello: TextView = view.findViewById(R.id.tvHello)
        val tvAndersen: TextView = view.findViewById(R.id.tvAndersen)

        tvHello.text = getString(R.string.hello)
        tvAndersen.text = getString(R.string.company_name)

        return view
    }
}