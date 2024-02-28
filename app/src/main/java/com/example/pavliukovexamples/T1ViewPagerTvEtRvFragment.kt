package com.example.pavliukovexamples

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.pavliukovexamples.databinding.T1FragmentViewPagerBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class T1ViewPagerTvEtRvFragment : Fragment() {
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var binding: T1FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = T1FragmentViewPagerBinding.inflate(layoutInflater)
        tabLayout = binding.t1TabLayout
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabNames: Array<String> = arrayOf(
            getString(R.string.editText),
            getString(R.string.textView),
            getString(R.string.recyclerView),
            getString(R.string.customView)
        )

        viewPager = binding.t1Viewpager
        viewPager.adapter = T1PagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, index -> tab.text = tabNames[index] }
            .attach()
    }
}