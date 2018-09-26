package com.funny.wanandroidclient.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.funny.wanandroidclient.ui.fragment.HomeFragment

/**
 * @author pengl
 */

class MainPageAdapter constructor(fm: FragmentManager) : FragmentPagerAdapter(fm){
    private var fragmentList = mutableListOf<Fragment>()

    private val tabNames = arrayOf("首页","知识体系")

    init {
        fragmentList.add(HomeFragment.newInstance())
        fragmentList.add(HomeFragment.newInstance())
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList.get(position)
    }

    override fun getCount(): Int {
        return tabNames.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabNames[position]
    }
}