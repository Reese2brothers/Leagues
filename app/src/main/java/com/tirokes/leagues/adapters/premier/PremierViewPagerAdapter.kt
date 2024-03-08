package com.tirokes.leagues.adapters.premier

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tirokes.leagues.fragments.premier.PremierAwayFragment
import com.tirokes.leagues.fragments.premier.PremierHomeFragment
import com.tirokes.leagues.fragments.premier.PremierOverallFragment

class PremierViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PremierOverallFragment()
            1 -> PremierAwayFragment()
            else -> PremierHomeFragment()
        }
    }
}