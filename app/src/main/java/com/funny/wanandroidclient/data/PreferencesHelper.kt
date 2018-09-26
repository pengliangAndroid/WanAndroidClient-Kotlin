package com.funny.wanandroidclient.data

import android.content.Context
import android.content.SharedPreferences



/**
 * @author pengl
 */
class PreferencesHelper{
    private val SETTING = "settings"

    private val IS_FIRST_RUN = "isFirstRun"
    private val HAS_AD_PAGE = "hasAdLoading"
    private val AD_PAGE_LOADING_TIME = "AdLoadingTime"
    private val AD_PAGE_ANCELABLE = "AdPageCancelable"
    private val AD_PAGE_URL = "AdPageUrl"
    private val LOGIN_VERSION_NAME = "loginVersionName"
    //private static final String LOGIN_USER_NAME = "loginUserName";

    private val OAUTH_TOKEN = "oauthToken"
    private val USER_INFO = "userInfo"

    private val LOGIN_INFO = "loginInfo"

    private var pref: SharedPreferences ? = null

    private var fileName: String = SETTING

    private var context: Context ? = null


    constructor(context: Context){
        this.context = context

        initPref()
    }

    constructor(context: Context, fileName: String){
        this.context = context
        this.fileName = fileName

        initPref()
    }

    private fun initPref(){
        pref = context?.getSharedPreferences(fileName, Context.MODE_PRIVATE)
    }

    fun getLoginInfo(): String {
        return pref!!.getString(LOGIN_INFO, "")
    }

    fun setLoginInfo(loginInfo: String) {
        val editor = pref!!.edit()
        editor.putString(LOGIN_INFO, loginInfo)
        editor.apply()
    }

    fun isFirstRun(): Boolean {
        return pref!!.getBoolean(IS_FIRST_RUN, true)
    }

    fun hasADPage(): Boolean {
        return pref!!.getBoolean(HAS_AD_PAGE, false)
    }

    fun setADPage(hasAdPage: Boolean) {
        val editor = pref!!.edit()
        editor.putBoolean(HAS_AD_PAGE, hasAdPage)
        editor.apply()
    }

    fun setIsFirstRun(isFirst: Boolean) {
        val editor = pref!!.edit()
        editor.putBoolean(IS_FIRST_RUN, isFirst)
        editor.apply()
    }

    fun getLoginVersionName(): String {
        return pref!!.getString(LOGIN_VERSION_NAME, "")
    }

    fun setLoginVersionName(versionName: String) {
        val editor = pref!!.edit()
        editor.putString(LOGIN_VERSION_NAME, versionName)
        editor.apply()
    }


    fun getOauthToken(): String {
        return pref!!.getString(OAUTH_TOKEN, "")
    }

    fun setOauthToken(oauthToken: String) {
        val editor = pref!!.edit()
        editor.putString(OAUTH_TOKEN, oauthToken)
        editor.apply()
    }

    fun getUserInfo(): String {
        return pref!!.getString(USER_INFO, "")
    }

    fun setUserInfo(userInfo: String) {
        val editor = pref!!.edit()
        editor.putString(USER_INFO, userInfo)
        editor.apply()
    }

    fun clearUserInfo() {
        remove(USER_INFO)
    }

    fun clearLoginInfo() {
        remove(LOGIN_INFO)
    }

    fun clearOauthToken() {
        remove(OAUTH_TOKEN)
    }

    fun remove(key: String) {
        pref!!.edit().remove(key).apply()
    }

    fun clear() {
        pref!!.edit().clear().apply()
    }



}