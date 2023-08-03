package com.example.pavliukovandersen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.pavliukovandersen.databinding.T3FragmentArticleBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class T3ArticleFragment : Fragment() {
    private lateinit var binding: T3FragmentArticleBinding
    private lateinit var title: String
    private lateinit var description: String
    private lateinit var sourceName: String
    private lateinit var urlToImage: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = arguments?.getString("title") ?: ""
        description = arguments?.getString("description") ?: ""
        sourceName = arguments?.getString("sourceName") ?: ""
        urlToImage = arguments?.getString("urlToImage") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = T3FragmentArticleBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.articleTitle.text = title
        binding.articleDescription.text = description
        binding.articleSourceName.text = sourceName

        if (urlToImage.isNotEmpty()) {
            lifecycleScope.launch {
                Picasso.get().load(urlToImage).into(binding.picassoIv)
                Glide.with(requireActivity()).load(urlToImage).into(binding.glideIv)
            }
            binding.picassoTv.visibility = View.VISIBLE
            binding.glideTv.visibility = View.VISIBLE
        }
    }

    companion object {
        fun newInstance(
            title: String,
            description: String,
            sourceName: String,
            urlToImage: String
        ) =
            T3ArticleFragment().apply {
                arguments = Bundle().apply {
                    putString("title", title)
                    putString("description", description)
                    putString("sourceName", sourceName)
                    putString("urlToImage", urlToImage)
                }
            }
    }

}