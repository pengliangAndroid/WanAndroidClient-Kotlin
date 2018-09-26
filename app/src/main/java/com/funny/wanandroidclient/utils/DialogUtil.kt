package com.funny.wanandroidclient.utils

import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.text.Html
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.funny.wanandroidclient.R


/**
 * @author pengl
 */
object DialogUtil{
    private var progressDialog : Dialog? = null

    fun dialogBuilder(context: Context, title: String?, msg: String?): AlertDialog.Builder {
        val builder = AlertDialog.Builder(context, AlertDialog.THEME_HOLO_LIGHT)
        if (msg != null) {
            builder.setMessage(msg)
        }
        if (title != null) {
            builder.setTitle(title)
        }
        return builder
    }

    fun dialogBuilder(context: Context, title: String?, msg: String?, isHtml: Boolean): AlertDialog.Builder {
        val builder = AlertDialog.Builder(context, AlertDialog.THEME_HOLO_LIGHT)
        if (msg != null) {
            builder.setMessage(Html.fromHtml(msg))
        }
        if (title != null) {
            builder.setTitle(title)
        }
        return builder
    }


    fun dialogBuilder(context: Context, title: Int, view: View?): AlertDialog.Builder {
        val builder = AlertDialog.Builder(context, AlertDialog.THEME_HOLO_LIGHT)
        if (view != null) {
            builder.setView(view)
        }
        if (title > 0) {
            builder.setTitle(title)
        }
        return builder
    }

    fun dialogBuilder(context: Context, titleResId: Int, msgResId: Int): AlertDialog.Builder {
        val title = if (titleResId > 0) context.getResources().getString(titleResId) else null
        val msg = if (msgResId > 0) context.getResources().getString(msgResId) else null
        return dialogBuilder(context, title, msg)
    }

    fun showTips(context: Context, title: String, des: String): Dialog {
        return showTips(context, title, des, null, null)
    }

    fun showTips(context: Context, title: Int, des: Int): Dialog {
        return showTips(context, context.getString(title), context.getString(des))
    }

    fun showTips(context: Context, title: Int, des: Int, btn: Int, dismissListener: DialogInterface.OnDismissListener): Dialog {
        return showTips(context, context.getString(title), context.getString(des), context.getString(btn), dismissListener)
    }

    fun showTips(context: Context, title: String, des: String, btn: String?, dismissListener: DialogInterface.OnDismissListener?): Dialog {
        val builder = dialogBuilder(context, title, des)
        builder.setCancelable(true)
        builder.setPositiveButton(btn, null)
        val dialog = builder.show()
        dialog.setCanceledOnTouchOutside(true)
        dialog.setOnDismissListener(dismissListener)
        return dialog
    }

    fun showProgressDialog(context: Context, message: String, cancelable: Boolean) {
        progressDialog = ProgressDialog.show(context, "", message)
        progressDialog?.setCancelable(cancelable)
    }

    fun stopProgressDialog() {
        if (progressDialog != null) {
            progressDialog?.dismiss()
            progressDialog = null
        }
    }

    fun showCustomProgressDialog(context: Context) {
        showCustomProgressDialog(context, R.string.msg_loading)
    }

    fun showCustomProgressDialog(context: Context, msgId: Int) {
        showCustomProgressDialog(context, context.getString(msgId))
    }

    fun showCustomProgressDialog(context: Context, msg: String) {
        if (progressDialog == null) {
            progressDialog = Dialog(context, R.style.ProgressDialogStyle)
        }
        progressDialog?.setContentView(R.layout.dialog_custom_loading)
        progressDialog?.setCancelable(true)
        progressDialog?.getWindow()?.setBackgroundDrawableResource(
                android.R.color.transparent)

        val progressBar = progressDialog?.findViewById(R.id.loading_progress) as ProgressBar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val drawable = context.getApplicationContext().getResources().getDrawable(R.drawable.progress_loading_v23)
            progressBar.setIndeterminateDrawable(drawable)
        }

        val text = progressDialog
                ?.findViewById(R.id.tv_loading_msg) as TextView
        text.text = msg
        progressDialog?.show()
    }

}