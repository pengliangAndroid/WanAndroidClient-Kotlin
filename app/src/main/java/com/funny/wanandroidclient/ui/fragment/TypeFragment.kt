package com.funny.wanandroidclient.ui.fragment

import android.os.Bundle
import android.view.View
import com.funny.wanandroidclient.R
import com.funny.wanandroidclient.base.BaseFragment

/**
 * @author pengl
 */
class TypeFragment: BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_type
    }

    override fun initViewsAndEvents(self: View?, savedInstanceState: Bundle?) {
    }

    override fun initData() {
    }

    companion object {
        fun newInstance(): TypeFragment{
            return TypeFragment()
        }
    }
}