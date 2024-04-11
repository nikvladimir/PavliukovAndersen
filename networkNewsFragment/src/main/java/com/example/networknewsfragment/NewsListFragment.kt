package com.example.networknewsfragment


import com.example.common.Constants

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.networknewsfragment.databinding.FragmentNewsListBinding
import com.example.networknewsfragment.objects.CryptoUtil
import com.example.networknewsfragment.retrofit.ArticleDto
import com.example.networknewsfragment.retrofit.RetrofitInstance
import com.example.networknewsfragment.retrofit.SourceDto
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class NewsListFragment : Fragment() {
    private lateinit var toolbar: Toolbar
    private lateinit var spinner: Spinner
    private lateinit var binding: FragmentNewsListBinding
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private val apiKey: String get() = CryptoUtil.getDecryptedKey()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsListBinding.inflate(layoutInflater)

        setupSpinner()
        setupRecyclerView()
        setupSwipeRefreshLayout()
        getNewsAndRefreshPosts()

        return binding.root
    }

    private fun setupSpinner() {
        toolbar = binding.toolBar
        spinner = binding.newsThemeSpinner
        val spinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            com.example.common.R.array.spinner_items,
            android.R.layout.simple_spinner_item
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                toolbar.title = selectedItem
                getNewsAndRefreshPosts(selectedItem)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter { selectedItem, view, transitionName ->
            openFragment(
                selectedItem,
                view,
                transitionName
            )
        }
        binding.recyclerViewNewsFragment.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }
    }

    private fun openFragment(item: ArticleDto, view: View, transitionName: String) {
        val articleFragment = ArticleFragment.newInstance(
            title = item.author,
            description = item.description,
            sourceName = item.source.name,
            urlToImage = item.urlToImage,
            itemTitle = item.title,
            transitionName = transitionName,
        )
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.hostNewsListFragment, articleFragment)
            addToBackStack(null)
            commit()
        }
    }

    private fun setupSwipeRefreshLayout() {
        swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            getNewsAndRefreshPosts(toolbar.title.toString())
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun getNewsAndRefreshPosts(topic: String = "software") {

        if (isNetworkAvailable()) {

            lifecycleScope.launch {

                val response = RetrofitInstance.api.queryAPI(
                    topic, getYesterdayDateFormatted(), Constants.NUMB_OF_TOPICS, apiKey
                )
                if (response.isSuccessful) {
                    val articles = response.body()?.articles
                    newsAdapter.submitList(articles)

                    if (articles?.size == 0) {
                        binding.recyclerViewNewsFragment.visibility = View.GONE
                        binding.notificationTV.text =
                            resources.getString(com.example.common.R.string.no_news_this_date)
                    } else {
                        binding.recyclerViewNewsFragment.visibility = View.VISIBLE
                        binding.notificationTV.text = ""
                    }

                } else {
                    val emptyData = listOf(ArticleDto("", "", "", SourceDto(""), "", ""))
                    newsAdapter.submitList(emptyData)
                    binding.recyclerViewNewsFragment.visibility = View.GONE
                    binding.notificationTV.text = resources
                        .getString(com.example.common.R.string.site_not_available)
                }
            }
        } else {
            binding.recyclerViewNewsFragment.visibility = View.GONE
            binding.notificationTV.text =
                resources.getString(com.example.common.R.string.no_network)
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: Network? = connectivityManager.activeNetwork
        val networkCapabilities: NetworkCapabilities? =
            connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapabilities != null &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    private fun getYesterdayDateFormatted(): String {
        val yesterdayDate = LocalDate.now().minusDays(1)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return yesterdayDate.format(formatter)
    }
}