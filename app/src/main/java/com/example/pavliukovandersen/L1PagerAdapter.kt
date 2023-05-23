package com.example.pavliukovandersen

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class L1PagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> L1TextViewFragment()
            1 -> EditTextFragment()
            else -> L1RecyclerViewFragment()
        }
    }
}