package com.falou.avaliacao_tecnica_falou.service

import com.falou.avaliacao_tecnica_falou.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceGenerator(){

    val innerRetrofit: Retrofit by lazy {
        initRetrofit()
    }

    private val url = "https://od-api.oxforddictionaries.com/api/v2/"

    private val gson: Gson by lazy { GsonBuilder().create() }

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }

    private fun initRetrofit(): Retrofit {

        return  Retrofit.Builder()
            .baseUrl(url)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    }


    inline fun <reified T> generate(): T {
        try {
            return innerRetrofit.create(T::class.java)
        } catch (e: Exception) {
            throw Exception("Not found service ${T::class.java}/ $e")
        }
    }
}