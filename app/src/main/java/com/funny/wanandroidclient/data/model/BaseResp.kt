package com.funny.androidtestdemo

/**
 * @author pengl
 */
data class BaseResp<T>(var errorCode: Int, var errorMsg: String, var data: T?)

data class PageBean<R>(var offset: Int,
                       var size: Int,
                       var total: Int,
                       var pageCount: Int,
                       var curPage: Int,
                       var over: Boolean,
                       var datas: List<R>?)