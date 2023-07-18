package com.example.pavliukovandersen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pavliukovandersen.databinding.T3FragmentArticleBinding

class T3FragmentArticle : Fragment() {
    private lateinit var binding: T3FragmentArticleBinding
    private lateinit var articleTitle: String
    private lateinit var articleDescription: String
    private lateinit var articleSourceName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = T3FragmentArticleBinding.inflate(layoutInflater)

        articleTitle = arguments?.getString("title") ?: ""
        articleDescription = arguments?.getString("description") ?: ""
        articleSourceName = arguments?.getString("sourceName") ?: ""

        binding.articleTitle.text = articleTitle
        binding.articleDescription.text = articleDescription
        binding.articleSourceName.text = articleSourceName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    companion object {
        fun newInstance(title: String, description: String, sourceName: String) =
            T3FragmentArticle().apply {
                arguments = Bundle().apply {
                    putString("title", title)
                    putString("description", description)
                    putString("sourceName", sourceName)
                }
            }
    }

}