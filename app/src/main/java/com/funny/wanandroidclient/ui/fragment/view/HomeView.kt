package com.funny.wanandroidclient.ui.fragment.view

import com.funny.wanandroidclient.mvp.MvpView

/**
 * @author pengl
 */
interface HomeView : MvpView{
    fun onGetArticalListSuccess()

    fun onGetArticalListFail()
}