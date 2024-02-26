package com.example.pavliukovandersen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pavliukovandersen.databinding.T4FragmentCustomViewBinding


class T4CustomViewFragment : Fragment() {
    private lateinit var binding: T4FragmentCustomViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = T4FragmentCustomViewBinding.inflate(layoutInflater)
        return binding.root
    }
}