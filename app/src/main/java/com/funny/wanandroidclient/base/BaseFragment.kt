package com.funny.wanandroidclient.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.funny.wanandroidclient.R
import com.funny.wanandroidclient.utils.DialogUtil
import com.funny.wanandroidclient.utils.ToastUtils



/**
 * @author pengl
 */
abstract class BaseFragment : Fragment(){
    protected var activity : BaseActivity? = null

    protected var rootView : View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //super.onCreateView(inflater, container, savedInstanceState)
        if (this.rootView == null) {
            this.rootView = inflater.inflate(this.getLayoutId(), container, false)
        }

        return this.rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        this.initViewsAndEvents(this.rootView, savedInstanceState)
        this.initData()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activity = context as BaseActivity
    }

    override fun onDetach() {
        super.onDetach()
        this.activity = null
    }

    fun getHoldingActivity(): BaseActivity {
        return activity!!
    }

    override fun onDestroyView() {
        super.onDestroyView()

        if (this.rootView?.getParent() != null) {
            val parent = this.rootView?.getParent() as ViewGroup
            parent.removeView(this.rootView)
        }
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initViewsAndEvents(self: View?, savedInstanceState: Bundle?)

    /**
     * Initialize the Activity data
     */
    protected abstract fun initData()


    fun showProgressDialog() {
        showProgressDialog("")
    }

    fun showProgressDialog(message: String) {
        showProgressDialog(message, true)
    }

    fun showProgressDialog(message: String, cancelable: Boolean) {
        DialogUtil.showProgressDialog(getHoldingActivity(), message, cancelable)
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
        DialogUtil.showCustomProgressDialog(getHoldingActivity(), msg)
    }

    fun showToast(message: String) {
        ToastUtils.showToast(getHoldingActivity(), message)
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
        ToastUtils.showCustomToast(getHoldingActivity(), text, duration)

    }

    fun cancelToast() {
        ToastUtils.cancelToast()
    }
}