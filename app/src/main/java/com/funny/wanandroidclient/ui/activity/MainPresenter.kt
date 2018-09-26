package com.funny.wanandroidclient.ui.activity

import android.content.Context
import com.funny.wanandroidclient.data.PreferencesHelper
import com.funny.wanandroidclient.mvp.BaseActPresenter

/**
 * @author pengl
 */
class MainPresenter : BaseActPresenter<MainView>(){

    fun getList(){
        val onLoadSuccess = mvpView?.onLoadSuccess()

        val pf = PreferencesHelper(baseActivity as Context)

    }

}