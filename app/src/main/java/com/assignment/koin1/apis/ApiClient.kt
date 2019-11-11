package com.assignment.koin1.apis


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by raghughuraiya on 17/08/17.
 */

object ApiClient {

    private const val TIMEOUT_CONNECT = 90
    private const val TIMEOUT_READ = 90

    private var retrofit: APIs? = null
    private val BASE_URL = "https://api.github.com/"

    val client: APIs
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                        .build().create(APIs::class.java)
            }
            return retrofit!!
        }

    private val okHttpClient: OkHttpClient
        get() {
            val logging = HttpLoggingInterceptor()
            val builder = OkHttpClient.Builder()
            builder.interceptors().add(logging)
            builder.connectTimeout(TIMEOUT_CONNECT.toLong(), TimeUnit.SECONDS)
            builder.readTimeout(TIMEOUT_READ.toLong(), TimeUnit.SECONDS)
            return builder.build()
        }
}