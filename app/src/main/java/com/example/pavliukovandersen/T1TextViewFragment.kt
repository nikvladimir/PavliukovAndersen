package com.example.pavliukovandersen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pavliukovandersen.databinding.T1FragmentTextViewBinding


class T1TextViewFragment : Fragment() {
    private lateinit var binding: T1FragmentTextViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = T1FragmentTextViewBinding.inflate(layoutInflater)

        binding.tvHello.text = getString(R.string.hello)
        binding.tvAndersen.text = getString(R.string.company_name)

        return binding.root
    }
}