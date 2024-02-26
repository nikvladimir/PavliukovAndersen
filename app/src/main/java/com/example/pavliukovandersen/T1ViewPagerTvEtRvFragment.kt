package com.example.pavliukovandersen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class T1ViewPagerTvEtRvFragment : Fragment() {
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.t1_fragment_view_pager, container, false)
        tabLayout = view.findViewById(R.id.t1_tab_layout_)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabNames: Array<String> = arrayOf(
            getString(R.string.editText),
            getString(R.string.textView),
            getString(R.string.recyclerView),
            getString(R.string.customView)
        )
        viewPager = view.findViewById(R.id.t1_viewpager)
        viewPager.adapter = T1PagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, index -> tab.text = tabNames[index] }
            .attach()
    }
}