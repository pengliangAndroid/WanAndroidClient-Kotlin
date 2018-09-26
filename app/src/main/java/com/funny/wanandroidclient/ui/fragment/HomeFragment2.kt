package com.funny.wanandroidclient.ui.fragment

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseViewHolder
import com.funny.wanandroidclient.R
import com.funny.wanandroidclient.base.BaseFragment
import com.funny.wanandroidclient.base.CommonAdapter
import kotlinx.android.synthetic.main.fragment_home.*



/**
 * @author pengl
 */
class HomeFragment2: BaseFragment(){

    private var adapter : CommonAdapter<String> ?= null

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initViewsAndEvents(self: View?, savedInstanceState: Bundle?) {

        val datas = ArrayList<String>()
        for (i in 1..15) {
            datas.add("$i")
        }

        adapter = DataAdapter(R.layout.item_name,datas)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = adapter

        scrollView.viewTreeObserver.addOnScrollChangedListener {
            if (swipeRefreshLayout != null) {
                swipeRefreshLayout.isEnabled = scrollView.scrollY == 0
            }
        }

        swipeRefreshLayout.setOnRefreshListener {
             swipeRefreshLayout.postDelayed({
                 swipeRefreshLayout.isRefreshing = false
             },2000)
        }

    }

    override fun initData() {

    }

    class DataAdapter(layoutResId: Int, datas: List<String>) : CommonAdapter<String>(layoutResId, datas){
        override fun convertViewItem(helper: BaseViewHolder?, item: String) {
            helper?.setText(R.id.tv_name,item)
        }
    }

    companion object {
        fun newInstance(): HomeFragment2{
            return HomeFragment2()
        }
    }

}