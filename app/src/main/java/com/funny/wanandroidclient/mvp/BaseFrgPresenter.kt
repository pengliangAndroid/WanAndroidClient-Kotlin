package com.funny.wanandroidclient.mvp

import com.funny.wanandroidclient.base.BaseActivity
import com.funny.wanandroidclient.base.BaseFragment

/**
 * @author pengl
 */
open class BaseFrgPresenter<T : MvpView> : BasePresenter<T>() {
    protected var baseFragment: BaseFragment? = null
    protected var baseActivity: BaseActivity? = null

    override fun attachView(mvpView: T) {
        super.attachView(mvpView)

        baseFragment = mvpView as BaseFragment
        baseActivity = baseFragment?.getHoldingActivity()
    }

    override fun detachView() {
        super.detachView()

        baseActivity = null
        baseFragment = null
    }
}