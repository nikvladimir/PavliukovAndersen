package com.example.pavliukovandersen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class T1ViewPagerTvEtRv : Fragment() {
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private var tabNames: Array<String> = arrayOf("TextView", "EditText", "RecyclerView")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.task1_view_pager, container, false)
        tabLayout = view.findViewById(R.id.tab_layout_l1)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = view.findViewById(R.id.lesson1_viewpager)
        viewPager.adapter = T1PagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, index -> tab.text = tabNames[index] }
            .attach()
    }
}