package com.example.pavliukovexamples

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class T1PagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {

    override fun getItemCount() = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> T1EditTextFragment()
            1 -> T1TextViewFragment()
            2 -> T1RecyclerViewFragment()
            else -> T1CustomViewFragment()
        }
    }
}