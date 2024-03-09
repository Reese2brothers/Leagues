package com.tirokes.leagues.adapters.championship

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tirokes.leagues.fragments.championship.ChampionshipAwayFragment
import com.tirokes.leagues.fragments.championship.ChampionshipHomeFragment
import com.tirokes.leagues.fragments.championship.ChampionshipOverallFragment

class ChampViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ChampionshipOverallFragment()
            1 -> ChampionshipAwayFragment()
            else -> ChampionshipHomeFragment()
        }
    }
}