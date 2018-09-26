package com.funny.wanandroidclient.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.funny.wanandroidclient.R
import com.funny.wanandroidclient.utils.DialogUtil
import com.funny.wanandroidclient.utils.ToastUtils




/**
 * @author pengl
 */

abstract class BaseActivity : AppCompatActivity() {

    var context : Context ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        context = this

        BaseActivityManager.getInstance().addActivity(this)

        initToolbar(savedInstanceState)
        initViewsAndEvents(savedInstanceState)
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()

        closeKeyboard()
        cancelToast()
        dismissProgressDialog()

        BaseActivityManager.getInstance().removeActivity(this)
        context = null
    }

    protected abstract fun getLayoutId() : Int

    protected abstract fun initToolbar(savedInstanceState: Bundle?)

    protected abstract fun initViewsAndEvents(savedInstanceState: Bundle?)

    protected abstract fun initData()


    fun showProgressDialog() {
        showProgressDialog("")
    }

    fun showProgressDialog(message: String) {
        showProgressDialog(message, true)
    }

    fun showProgressDialog(message: String, cancelable: Boolean) {
        DialogUtil.showProgressDialog(this, message, cancelable)
    }

    @Deprecated("")
    fun stopProgressDialog() {
        DialogUtil.stopProgressDialog()
    }

    fun dismissProgressDialog() {
        DialogUtil.stopProgressDialog()
    }

    fun showCustomProgressDialog() {
        showCustomProgressDialog(R.string.msg_loading)
    }

    fun showCustomProgressDialog(msgId: Int) {
        showCustomProgressDialog(getString(msgId))
    }

    fun showCustomProgressDialog(msg: String) {
        DialogUtil.showCustomProgressDialog(this, msg)
    }

    fun showToast(message: String) {
        ToastUtils.showToast(this, message)
    }

    fun showToast(messageId: Int) {
        showToast(getString(messageId))
    }

    fun showCustomToast(msgId: Int) {
        showCustomToast(getString(msgId))
    }

    fun showCustomToast(message: String) {
        showCustomToast(message, Toast.LENGTH_SHORT)
    }

    fun showCustomToast(text: CharSequence, duration: Int) {
        ToastUtils.showCustomToast(this, text, duration)
    }

    fun cancelToast() {
        ToastUtils.cancelToast()
    }

    fun closeKeyboard(){
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(window.decorView.windowToken,0)
    }
}