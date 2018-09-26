package com.funny.wanandroidclient.base

import android.app.Activity
import java.util.*


/**
 * @author pengl
 */

class BaseActivityManager private constructor(){

    companion object {
        private val TAG = "BaseActivityManager"

        private val activityClass = LinkedList<Activity>()

        private var instance: BaseActivityManager? = null

        fun getInstance() : BaseActivityManager{
            if(instance == null){
                synchronized(BaseActivityManager::class.java){
                    if(instance == null)
                        instance = BaseActivityManager()
                }
            }

            return instance!!
        }
    }


    fun size(): Int{
        return activityClass.size
    }

    @Synchronized fun addActivity(activity: Activity) {
        activityClass.add(activity)
    }

    @Synchronized fun removeActivity(activity:Activity) {
        if (activityClass.contains(activity)) {
            activityClass.remove(activity)
        }
    }

    @Synchronized fun clear() {
        for(act in activityClass){
            removeActivity(act)
            act.finish()
        }
    }

    val forwardActivity : Activity ?
    @Synchronized get() = if(size() > 0) activityClass[size() - 1] else null

    fun contains(name: String): Boolean{
        for (act in activityClass){
            if(act.javaClass.simpleName == name){
                return true
            }
        }

        return false
    }

}
