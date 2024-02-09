package com.mironov.tinkofftesttask.data.remote.api

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class KeyInterceptor @Inject constructor() : Interceptor {
    companion object {

        private const val KEY_PARAM = "X-API-KEY"
        private const val API_KEY = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder().addHeader(KEY_PARAM, API_KEY)
        return chain.proceed(requestBuilder.build())
    }
}