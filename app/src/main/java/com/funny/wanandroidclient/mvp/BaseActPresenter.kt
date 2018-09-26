package com.funny.wanandroidclient.mvp

import com.funny.wanandroidclient.base.BaseActivity

/**
 * @author pengl
 */
open class BaseActPresenter<T : MvpView> : BasePresenter<T>() {
    protected var baseActivity: BaseActivity? = null

    override fun attachView(mvpView: T) {
        super.attachView(mvpView)

        baseActivity = mvpView as BaseActivity
    }

    override fun detachView() {
        super.detachView()

        baseActivity = null
    }
}