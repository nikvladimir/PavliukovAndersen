package com.example.pavliukovandersen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class TextViewFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_text_view, container, false)

        val helloTextView: TextView = view.findViewById(R.id.helloTextView)
        val andersenTextView: TextView = view.findViewById(R.id.andersenTextView)

        helloTextView.text = "Hello"
        andersenTextView.text = "Andersen"

        return view
    }
}