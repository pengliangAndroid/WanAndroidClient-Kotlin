package com.funny.wanandroidclient

import android.app.Application

/**
 * @author pengl
 */

class WApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        loge("WApplication OnCreate")
    }


}