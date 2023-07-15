package com.example.pavliukovandersen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pavliukovandersen.databinding.T3FragmentNewsListBinding
import com.example.pavliukovandersen.retrofit.NewsAPIInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class T3NewsListFragment : Fragment() {
    private lateinit var toolbar: Toolbar
    private lateinit var spinner: Spinner
    private lateinit var binding: T3FragmentNewsListBinding
    private lateinit var adapter: T3NewsAdapter


    private val baseNewsUrl = ("https://newsapi.org")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = T3FragmentNewsListBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        // Toolbar and spinner filter logic
        toolbar = binding.t3ToolBar
        spinner = binding.t3Spinner
        val spinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_items,
            android.R.layout.simple_spinner_item
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                toolbar.title = selectedItem
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
        // GET from internet logic
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseNewsUrl).client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val newsApi = retrofit.create(NewsAPIInterface::class.java)

        // recyclerView logic
        adapter = T3NewsAdapter()
        binding.t3RecyclerViewNewsFragment.layoutManager = LinearLayoutManager(requireContext())
        binding.t3RecyclerViewNewsFragment.adapter = adapter

        CoroutineScope(Dispatchers.IO).launch {
            val news = newsApi.getAllNews()
            requireActivity().runOnUiThread {
                adapter.submitList(news.articles)
            }
        }
        return binding.root
    }
}