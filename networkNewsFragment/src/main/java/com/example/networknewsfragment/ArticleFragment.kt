package com.example.networknewsfragment

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.networknewsfragment.databinding.FragmentArticleBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class ArticleFragment : Fragment() {
    private lateinit var binding: FragmentArticleBinding
    private lateinit var title: String
    private lateinit var description: String
    private lateinit var sourceName: String
    private lateinit var urlToImage: String
    private lateinit var itemTitle: String
    private lateinit var transitionName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)

        title = arguments?.getString("title") ?: ""
        description = arguments?.getString("description") ?: ""
        sourceName = arguments?.getString("sourceName") ?: ""
        urlToImage = arguments?.getString("urlToImage") ?: ""
        itemTitle = arguments?.getString("itemTitle") ?: ""
        transitionName = arguments?.getString("transitionName") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val articleTitle = binding.articleTitle

        articleTitle.text = itemTitle
        articleTitle.transitionName = transitionName

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

        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.move)
    }

    companion object {
        fun newInstance(
            title: String,
            description: String,
            sourceName: String,
            urlToImage: String,
            itemTitle: String,
            transitionName: String
        ) =
            ArticleFragment().apply {
                arguments = Bundle().apply {
                    putString("title", title)
                    putString("description", description)
                    putString("sourceName", sourceName)
                    putString("urlToImage", urlToImage)
                    putString("itemTitle", itemTitle)
                    putString("transitionName", transitionName)
                }
            }
    }

}