package com.example.viewexamples

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.viewexamples.databinding.FragmentTextViewBinding


class TextViewFragment : Fragment() {
    private lateinit var binding: FragmentTextViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTextViewBinding.inflate(layoutInflater)

        binding.tvHello.text = getString(R.string.hello)
        binding.tvWorld.text = getString(R.string.company_name)

        return binding.root
    }
}