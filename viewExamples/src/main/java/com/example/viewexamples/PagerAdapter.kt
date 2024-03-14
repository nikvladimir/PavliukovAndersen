package com.example.viewexamples

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {

    override fun getItemCount() = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> EditTextFragment()
            1 -> TextViewFragment()
            2 -> RecyclerViewFragment()
            else -> CustomViewFragment()
        }
    }
}