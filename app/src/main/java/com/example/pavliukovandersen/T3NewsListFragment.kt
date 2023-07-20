package com.example.pavliukovandersen

import android.os.Bundle
import android.util.Log
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
import com.example.pavliukovandersen.databinding.T3FragmentNewsListBinding
import com.example.pavliukovandersen.retrofit.ArticleDto
import com.example.pavliukovandersen.retrofit.RetrofitInstance
import com.example.pavliukovandersen.retrofit.SourceDto
import kotlinx.coroutines.launch
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import java.util.Base64
import java.util.Calendar

class T3NewsListFragment : Fragment() {
    private lateinit var toolbar: Toolbar
    private lateinit var spinner: Spinner
    private lateinit var binding: T3FragmentNewsListBinding
    private lateinit var newsAdapter: T3NewsAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private val apiKey: String get() = getKey()

    @get:JvmName("retrieveCurrentDate")
    val currentDate: String
        get() = getCurrentDate()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = T3FragmentNewsListBinding.inflate(layoutInflater)

        setupSpinner()
        setupRecyclerView()
        setupSwipeRefreshLayout()
        getNewsAndRefreshPosts("software")

        return binding.root
    }

    private fun setupSpinner() {
        toolbar = binding.t3ToolBar
        spinner = binding.t3NewsThemeSpinner
        val spinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(), R.array.spinner_items, android.R.layout.simple_spinner_item
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

    private fun getNewsAndRefreshPosts(topic: String) {
        lifecycleScope.launch {
            val response =
                RetrofitInstance.api.queryAPI(topic, "2023-07-15", Constants.NUMB_OF_TOPICS, apiKey)
//                RetrofitInstance.api.queryAPI(topic, currentDate, Constants.NUMB_OF_TOPICS, apiKey)

            if (response.isSuccessful) newsAdapter.submitList(response.body()?.articles)
            else newsAdapter.submitList(
                listOf(
                    ArticleDto("", "", "", SourceDto(""), "")
                )
            )

        }
    }

    private fun setupSwipeRefreshLayout() {
        swipeRefreshLayout = binding.t3SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            getNewsAndRefreshPosts(toolbar.title.toString())
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setupRecyclerView() {
        newsAdapter = T3NewsAdapter { selectedItem -> openFragment(selectedItem) }
        binding.t3RecyclerViewNewsFragment.layoutManager = LinearLayoutManager(requireContext())
        binding.t3RecyclerViewNewsFragment.adapter = newsAdapter
    }


    private fun openFragment(item: ArticleDto) {
        val fragmentManager = this@T3NewsListFragment.parentFragmentManager
        val new = T3FragmentArticle.newInstance(item.author, item.description, item.source.name)

        fragmentManager
            .beginTransaction()
            .replace(R.id.displayed_screen_fl, new)
            .addToBackStack(null)
            .commit()
    }

    private fun getKey(): String {
        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, SecretKeySpec("0123456789abcdef".toByteArray(), "AES"))
        return String(cipher.doFinal(Base64.getDecoder().decode(Constants.KEY)))
    }

    private fun getCurrentDate(): String {
        val c = Calendar.getInstance()
        return "${c.get(Calendar.YEAR)}-${c.get(Calendar.MONTH) + 1}-${c.get(Calendar.DAY_OF_MONTH)}"
    }
}