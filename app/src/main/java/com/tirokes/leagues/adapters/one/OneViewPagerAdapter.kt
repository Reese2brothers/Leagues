package com.tirokes.leagues.adapters.one

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tirokes.leagues.fragments.one.OneAwayFragment
import com.tirokes.leagues.fragments.one.OneHomeFragment
import com.tirokes.leagues.fragments.one.OneOverallFragment

class OneViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OneOverallFragment()
            1 -> OneAwayFragment()
            else -> OneHomeFragment()
        }
    }
}