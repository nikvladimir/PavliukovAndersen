package com.example.pavliukovexamples

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pavliukovexamples.databinding.T1FragmentCustomViewBinding


class T1CustomViewFragment : Fragment() {
    private lateinit var binding: T1FragmentCustomViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = T1FragmentCustomViewBinding.inflate(layoutInflater)
        return binding.root
    }
}