package com.funny.wanandroidclient.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import com.funny.wanandroidclient.R
import com.funny.wanandroidclient.base.BaseToolbarActivity
import com.funny.wanandroidclient.loge
import com.funny.wanandroidclient.ui.fragment.HomeFragment2
import com.funny.wanandroidclient.ui.fragment.TypeFragment
import kotlinx.android.synthetic.main.activity_main.*




class MainActivity : BaseToolbarActivity() {

    private var menuItems: MutableMap<Int,MenuItem> ? = null
    private var drawerToggle: ActionBarDrawerToggle ? = null

    private var homeFragment : Fragment? = null
    private var typeFragment : Fragment? = null

    private var curIndex: Int = -1

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initViewsAndEvents(savedInstanceState: Bundle?) {
        showBack()
        actionBarHelper?.setTitle(getString(R.string.app_name))

        initDrawerLayout()

        setTabSelection(0)

        bottomNavView.setOnNavigationItemSelectedListener{
            loge("item:"+it.title)
            if(it.itemId == R.id.navigation_home){
                setTabSelection(0)
            }else if(it.itemId == R.id.navigation_type){
                setTabSelection(1)
            }

            return@setOnNavigationItemSelectedListener true
        }


        /*tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        val mainPagerAdapter = MainPageAdapter(supportFragmentManager)

        viewPager.offscreenPageLimit = mainPagerAdapter.count.minus(1)
        viewPager.adapter = mainPagerAdapter
        tabLayout.setupWithViewPager(viewPager)*/
    }

    override fun initData() {

    }

    private fun setTabSelection(index: Int) {
        if (index == curIndex)
            return
        // 开启一个Fragment事务
        val transaction = supportFragmentManager.beginTransaction()

        curIndex = index

        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction)

        when (index) {
            0 -> {
                if (homeFragment == null) {
                    homeFragment = HomeFragment2.newInstance()
                    transaction.add(R.id.flContent, homeFragment!!, homeFragment!!.javaClass.simpleName)
                } else {
                    transaction.show(homeFragment!!)
                }
            }
            1 -> {
                if (typeFragment == null) {
                    typeFragment = TypeFragment.newInstance()
                    transaction.add(R.id.flContent, typeFragment!!, typeFragment!!.javaClass.simpleName)
                } else {
                    transaction.show(typeFragment!!)
                }
            }
        }
        transaction.commitAllowingStateLoss()
    }



    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment!!)
        }

        if (typeFragment != null) {
            transaction.hide(typeFragment!!)
        }
    }


    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }*/

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
        } else {
            return super.onOptionsItemSelected(item)
        }

        return true
    }

    private fun initDrawerLayout(){
        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_settings -> drawerLayout.closeDrawer(navView)
                R.id.nav_site -> {

                }
                R.id.nav_favorite -> {

                }
            }

            return@setNavigationItemSelectedListener true
        }

        // 初始化MenuItems
        menuItems = mutableMapOf()
        val menuItemIds = intArrayOf(R.id.nav_home, R.id.nav_site, R.id.nav_favorite)

        for (id in menuItemIds) {
            val menuItem = navView.menu.findItem(id) as MenuItem
            menuItems?.put(id, menuItem)
        }
        drawerToggle = ActionBarDrawerToggle(this, this.drawerLayout, R.string.app_menu,
                R.string.app_name)

        drawerToggle?.syncState()
        drawerLayout.addDrawerListener(EasyDrawerListener())
    }

    inner class EasyDrawerListener : DrawerLayout.DrawerListener{
        override fun onDrawerStateChanged(newState: Int) {
            drawerToggle?.onDrawerStateChanged(newState)
        }

        override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
            drawerToggle?.onDrawerSlide(drawerView,slideOffset)
        }

        override fun onDrawerClosed(drawerView: View) {
            drawerToggle?.onDrawerClosed(drawerView)
        }

        override fun onDrawerOpened(drawerView: View) {
            drawerToggle?.onDrawerOpened(drawerView)
        }

    }
}
