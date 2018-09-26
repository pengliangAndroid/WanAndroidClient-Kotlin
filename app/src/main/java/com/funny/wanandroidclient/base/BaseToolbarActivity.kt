package com.funny.wanandroidclient.base

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import com.funny.wanandroidclient.R


/**
 * @author pengl
 */
abstract class BaseToolbarActivity : BaseActivity(){
    protected var toolbar : Toolbar? = null

    protected var actionBarHelper : ActionBarHelper? = null

    override fun initToolbar(savedInstanceState: Bundle?) {
        toolbar = findViewById(R.id.toolbar)
        //appBarLayout = (AppBarLayout) findViewById(R.id.app_layout);

        if(toolbar != null){
            this.initToolbarHelper()
        }
    }


    /**
     * init the toolbar
     */
    protected fun initToolbarHelper() {
        //if (this.toolbar == null || this.mAppBarLayout == null) return;

        this.setSupportActionBar(this.toolbar)

        this.actionBarHelper = this.createActionBarHelper()
        this.actionBarHelper?.init()

        /*if (Build.VERSION.SDK_INT >= 21) {
            this.mAppBarLayout.setElevation(5.0f);
        }*/
    }


    /**
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     * @se.onCreateOptionsMenu
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            this.onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }


    protected fun showBack() {
        if (this.actionBarHelper != null) this.actionBarHelper?.setDisplayHomeAsUpEnabled(true)
    }


    /**
     * set the AppBarLayout alpha
     *
     * @param alpha alpha
     */
    /* protected void setAppBarLayoutAlpha(float alpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            this.mAppBarLayout.setAlpha(alpha);
        }
    }*/


    /**
     * set the AppBarLayout visibility
     *
     * @param visibility visibility
     */
    protected fun setToolBarLayoutVisibility(visibility: Boolean) {
        if (visibility) {
            //this.mAppBarLayout.setVisibility(View.VISIBLE);
            this.toolbar?.setVisibility(View.VISIBLE)
        } else {
            //this.mAppBarLayout.setVisibility(View.GONE);
            this.toolbar?.setVisibility(View.GONE)
        }
    }


    /**
     * Create a compatible helper that will manipulate the action bar if available.
     */
    fun createActionBarHelper(): ActionBarHelper {
        return ActionBarHelper()
    }


    inner class ActionBarHelper {
        private var actionBar: ActionBar? = null
        private var drawerTitle: CharSequence? = null
        private var title: CharSequence? = null

        init {
            this.actionBar = supportActionBar
        }


        fun init() {
            this.actionBar?.setDisplayHomeAsUpEnabled(true)
            this.actionBar?.setDisplayShowHomeEnabled(false)
            drawerTitle = getTitle()
            this.title = drawerTitle
        }


        fun onDrawerClosed() {
            if (this.actionBar == null) return
            this.actionBar!!.setTitle(this.title)
        }


        fun onDrawerOpened() {
            if (this.actionBar == null) return
            this.actionBar!!.setTitle(this.drawerTitle)
        }


        fun setTitle(title: CharSequence) {
            this.title = title
        }


        fun setDrawerTitle(drawerTitle: CharSequence) {
            this.drawerTitle = drawerTitle
        }


        fun setDisplayHomeAsUpEnabled(showHomeAsUp: Boolean) {
            this.actionBar?.setDisplayHomeAsUpEnabled(showHomeAsUp)
        }
    }
}