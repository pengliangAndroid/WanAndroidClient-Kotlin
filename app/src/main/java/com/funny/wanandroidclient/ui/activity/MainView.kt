package com.funny.wanandroidclient.ui.activity

import com.funny.wanandroidclient.mvp.MvpView

/**
 * @author pengl
 */

interface MainView : MvpView{
    fun onLoadSuccess()

    fun onLoadFail(error: String)
}