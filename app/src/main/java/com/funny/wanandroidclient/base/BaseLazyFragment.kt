package com.funny.wanandroidclient.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * @author pengl
 */
abstract class BaseLazyFragment : BaseFragment(){
    //是否用户可见
    protected var visiable = false

    //是否加载过数据
    protected var isLoadData = false

    private var isPrepared: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if (userVisibleHint) {
            visiable = true
            //只有用户可见时进行加载
            lazyLoad()
        } else {
            visiable = false
            onInVisible()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isPrepared = true
        lazyLoad()
    }


    protected abstract fun loadData()

    fun onInVisible() {}

    protected fun lazyLoad() {
        if (!isPrepared || !isVisible || isLoadData) {
            return
        }

        loadData()
        isLoadData = true
    }

}