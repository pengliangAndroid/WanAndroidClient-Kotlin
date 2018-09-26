package com.funny.wanandroidclient.mvp



/**
 * @author pengl
 */
interface Presenter<T : MvpView> {
    fun attachView(mvpView: T)

    fun detachView()
}