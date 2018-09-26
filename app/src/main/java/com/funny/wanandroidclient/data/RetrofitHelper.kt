package com.funny.wanandroidclient.data

import android.util.Log
import com.funny.wanandroidclient.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author pengl
 */
object RetrofitHelper{
    private const val TAG = "RetrofitHelper"

    private const val DEFAULT_TIMEOUT = 30L

    var homeRestApi : HomeRestApi

    //private var okHttpClient : OkHttpClient
    //private var retrofit : Retrofit

    init {
        Log.d("RetrofitHelper","init")
        val okHttpClient = createOkHttpClient()
        val retrofit = createRetrofit(HttpConstants.BASE_URL, okHttpClient)
        homeRestApi = getService(retrofit,HomeRestApi::class.java)
    }

    private fun createOkHttpClient() : OkHttpClient{
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS)
        builder.readTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS)
        builder.writeTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS)

        if(BuildConfig.DEBUG){
            val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { msg ->
                Log.d(TAG,msg)
            }).apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            builder.addInterceptor(loggingInterceptor)
        }

        return builder.build()
    }

    private fun createRetrofit(url : String,client: OkHttpClient) : Retrofit{
       return Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
    }

    private fun <T> getService(retrofit: Retrofit,clazz : Class<T>) : T{
        return retrofit.create(clazz)
    }
}