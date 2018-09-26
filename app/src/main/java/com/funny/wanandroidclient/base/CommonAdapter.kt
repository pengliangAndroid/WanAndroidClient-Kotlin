package com.funny.wanandroidclient.base

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder



/**
 * @author pengl
 */
open abstract class CommonAdapter<T>(layoutResId : Int,datas: List<T>) : BaseQuickAdapter<T,BaseViewHolder>(layoutResId,datas){
    override fun convert(helper: BaseViewHolder?, item: T) {
        convertViewItem(helper,item)
    }

    abstract fun convertViewItem(helper: BaseViewHolder?, item: T)
}