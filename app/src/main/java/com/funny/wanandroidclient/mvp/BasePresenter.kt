package com.funny.wanandroidclient.mvp

/**
 * @author pengl
 */
open class BasePresenter<T : MvpView> : Presenter<T>{
    protected var mvpView : T? = null

    override fun attachView(mvpView: T) {
        this.mvpView = mvpView
    }

    override fun detachView() {
        mvpView = null
    }

}