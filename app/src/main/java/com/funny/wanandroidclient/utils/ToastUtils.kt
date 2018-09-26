package com.funny.wanandroidclient.utils

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import com.funny.wanandroidclient.R


/**
 * @author pengl
 */
object ToastUtils{
    private var toast: Toast? = null

    fun showToast(context: Context, message: String) {
        if (toast == null) {
            toast = Toast.makeText(context.applicationContext, message, Toast.LENGTH_SHORT)
        } else {
            toast?.setText(message)
        }
        toast?.show()
    }

    fun showToast(context: Context, messageId: Int) {
        showToast(context, context.resources.getString(messageId))
    }

    fun showCustomToast(context: Activity, msgId: Int) {
        showCustomToast(context, context.resources.getString(msgId))
    }

    fun showCustomToast(context: Activity, message: String) {
        showCustomToast(context, message, Toast.LENGTH_SHORT)
    }

    fun showCustomToast(context: Activity, text: CharSequence, duration: Int) {
        if (toast == null) {
            //toast = Toast.makeText(this, text, duration);
            toast = Toast(context)

            val view = context.layoutInflater.inflate(R.layout.toast_custom_view, null)
            val textView = view
                    .findViewById(R.id.tv_msg) as TextView
            textView.text = text
            toast?.view = view
            toast?.setGravity(Gravity.CENTER, 0, 0)
            toast?.duration = duration
        } else {
            val textView = toast?.view
                    ?.findViewById(R.id.tv_msg) as TextView
            textView.text = text
        }

        toast?.show()
    }

    fun cancelToast() {
        if (toast != null) {
            toast?.cancel()
        }
    }
}