package com.funny.wanandroidclient

import android.annotation.SuppressLint
import android.content.Context
import android.support.annotation.LayoutRes
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.JobCancellationException

/**
 * @author pengl
 */


/**
 * Log
 */
fun loge(tag: String, content: String?) {
    Log.e(tag, content ?: tag)
}


fun loge(content: String?) {
    Log.e("Log", content ?: "")
}


/**
 * show toast
 * @param content String
 */
@SuppressLint("ShowToast")
/*fun Context.toast(content: String) {
    HttpConstants.showToast?.apply {
        setText(content)
        show()
    } ?: run {
        Toast.makeText(this.applicationContext, content, Toast.LENGTH_SHORT).apply {
            HttpConstants.showToast = this
        }.show()
    }
}*/

/**
 * show toast
 * @param id strings.xml
 */
/*
fun Context.toast(@StringRes id: Int) {
    toast(getString(id))
}
*/

fun Deferred<Any>?.cancelByActive() = this?.run {
    tryCatch {
        if (isActive) {
            cancel()
        }
    }
}


/**
 * LayoutInflater.from(this).inflate
 * @param resource layoutId
 * @return View
 */
fun Context.inflater(@LayoutRes resource: Int): View =
        LayoutInflater.from(this).inflate(resource, null)

/**
 * In disappear assist cheng (cancel) will be submitted to the Job Cancellation Exception Exception.
 */
inline fun tryCatch(catchBlock: (Throwable) -> Unit = {}, tryBlock: () -> Unit) {
    try {
        tryBlock()
    } catch (_: JobCancellationException) {

    } catch (t: Throwable) {
        catchBlock(t)
    }
}