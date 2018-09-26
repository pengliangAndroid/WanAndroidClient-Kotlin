package com.funny.wanandroidclient.data

import com.funny.androidtestdemo.Article
import com.funny.androidtestdemo.BaseResp
import com.funny.androidtestdemo.PageBean
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author pengl
 */

interface HomeRestApi{

    /**
     * 获取文章列表
     */
    @GET("/article/list/{page}/json")
    fun getHomeList(
            @Path("page") page: Int
    ): Deferred<BaseResp<PageBean<Article>>>


}