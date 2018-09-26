package com.funny.wanandroidclient.ui.fragment.presenter

import com.funny.wanandroidclient.mvp.BasePresenter
import com.funny.wanandroidclient.ui.fragment.view.HomeView

/**
 * @author pengl
 */
class HomePresenter: BasePresenter<HomeView>() {
    var curPage = 0

    var hasMoreData = true

    fun getArticalList(){
        //RetrofitHelper.homeRestApi.getHomeList()
    }

}